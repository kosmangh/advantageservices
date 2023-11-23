package com.sabonay.advantageservices.services;

import com.sabonay.advantageservices.models.reports.DemandNoticeInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author dainoo
 */
@Stateless
public class ResportServices implements Serializable {
    
    
    

    public List<DemandNoticeInfo> generateDemandNotices(int count) {
        List<DemandNoticeInfo> demandNoticeList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            DemandNoticeInfo demandNotice = new DemandNoticeInfo();
            demandNotice.setLessee("Lessee " + i);
            demandNotice.setStreetName("Street " + i);
            demandNotice.setFileNo("File " + i);
            demandNotice.setPropertyNo("Property " + i);
            demandNotice.setPropertyClass("Class " + i);
            demandNotice.setLocation("Location " + i);
            demandNotice.setCurrentCharge((double) (i * 1000));
            demandNotice.setArrears((double) (i * 500));
            demandNotice.setTotalAmountDue((double) (i * 1500));
            demandNotice.setLesseExpiryDate(new Date());
            demandNoticeList.add(demandNotice);
        }
        return demandNoticeList;
    }
    
    
    
}
