package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.models.reports.DemandNoticeInfo;
import com.sabonay.advantageservices.reports.ReportFiles;
import com.sabonay.advantageservices.restmodels.billpayment.DemandNoticeRequest;
import com.sabonay.advantageservices.services.BasicServices;
import com.sabonay.advantageservices.services.BillPaymentServices;
import com.sabonay.advantageservices.services.ResportServices;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

/**
 *
 * @author dainoo
 */
@RequestScoped
@Path("reports")
public class ReportsController {

    private static final Logger log = Logger.getLogger(ReportsController.class.getName());

    @Inject
    private ResportServices resportServices;
    @Inject
    private BasicServices basicJPA;
    @Inject
    private BillPaymentServices billPaymentServices;

    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/generatedemandnoticereport")
    public Response generateReportPDF(DemandNoticeRequest request) throws IOException {

        try {
            System.out.println("Printing");
            log.info("setting parameters before report file");

            // Load the Jasper report file
            InputStream reportInputStream = getClass().getResourceAsStream(ReportFiles.DEMAND_NOTICE_REPORT);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportInputStream);

            log.info("setting parameters");
            //find the zone
            EstateZone zone = basicJPA.find(EstateZone.class, request.getHeaderRequest().getZone());

            //get demand notice list
            // Set report parameters
            String contacts = zone.getContactNo();
            if (null != zone.getOtherContactNo() || !zone.getOtherContactNo().isEmpty()) {
                contacts += " / " + zone.getOtherContactNo();
            }
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("zoneName", zone.getZoneName());
            parameters.put("zoneAddress", zone.getAddress());
            parameters.put("zoneContact", contacts);
            parameters.put("zoneEmail", zone.getEmail());
            parameters.put("zoneWebsite", zone.getWebsite());

            parameters.put("reportTitle", "GROUND RENT DEMAND NOTICE FOR " + request.getChargeYear());
            parameters.put("footerTitle", "State Housing Company Limited");
            parameters.put("printedBy", "Daud");
            parameters.put("noticeHeader", "Please TAKE NOTICE that ground rent in respect of the under-mentioned property for the year ending "
                    + "31-December " + request.getChargeYear() + " became due on 01-January " + request.getChargeYear() + ".");
            parameters.put("noticeA", "Ground Rents are payable to an accredited official of the State Housing Company Limited or any"
                    + " <b>" + zone.getBankName() + " ( " + zone.getBankBranch() + ","
                    + " A/C No. - " + zone.getAccountNo() + " )</b>, Clients who pay through the bank should present their deposit Slip for their receipt later from our Main office."
                    + " (Please indicate your File No, on the deposit Slip).\nAlternatively, you may wish to settle the Total Amount Due at the State Housing Co, Ltd., Main Office, Elfiakuma Now Site, Takoradi Technical University - Time Ent. Road");
            parameters.put("noticeB", "The company reserves the right to take any action including the law courts to recover all ground rent, one month after it became due together with costs without further notice.");
            parameters.put("noticeC", "In accordance with the lease com payment of the ground rent reserved constitute a breach of covenant whereby the company is entitled forthwith to re-enter and terminate the lease without any right of compensation to you.");
            parameters.put("noticeD", "Please note that ground rent shall be reviewed yearly, and any arrears shall be paid at current rate/value.");
            parameters.put("shc_logo", getClass().getResourceAsStream(ReportFiles.SHC_LOGO));
            parameters.put("coat_of_arms", getClass().getResourceAsStream(ReportFiles.COAT_OF_ARMS));

            List<DemandNoticeInfo> summaryResponse = billPaymentServices.generateDemandNotice(request).getDemandNotices();
            AppLogger.printPayload(log, "data reponse", summaryResponse);

            // Create a data source for the report
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(summaryResponse);

            // Fill the report with data
            JasperPrint jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export the report to PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            // Set response headers
            Response.ResponseBuilder responseBuilder = Response.ok(outputStream.toByteArray());
            responseBuilder.header("Content-Disposition", "attachment; filename=report.pdf");
            responseBuilder.header("Content-Type", "application/pdf");

            // Return the response with the PDF
            return responseBuilder.build();

        } catch (JRException e) {
            AppLogger.error(log, e, "Error printing");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating report.").build();
        }
    }
}
