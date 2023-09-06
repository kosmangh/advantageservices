package com.sabonay.advantageservices.securities;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Daud
 */
@javax.ws.rs.ApplicationPath("")
public class Config extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.sabonay.advantageservices.controllers.AuthController.class);
        resources.add(com.sabonay.advantageservices.controllers.BillPaymentController.class);
        resources.add(com.sabonay.advantageservices.controllers.DepartmentController.class);
        resources.add(com.sabonay.advantageservices.controllers.DropdownController.class);
        resources.add(com.sabonay.advantageservices.controllers.EstateBlockController.class);
        resources.add(com.sabonay.advantageservices.controllers.EstateController.class);
        resources.add(com.sabonay.advantageservices.controllers.EstatePropertyController.class);
        resources.add(com.sabonay.advantageservices.controllers.GroundRentBillingController.class);
        resources.add(com.sabonay.advantageservices.controllers.OccupantController.class);
        resources.add(com.sabonay.advantageservices.controllers.OccupantPropertyController.class);
        resources.add(com.sabonay.advantageservices.controllers.PropertyChargeController.class);
        resources.add(com.sabonay.advantageservices.controllers.RegionController.class);
        resources.add(com.sabonay.advantageservices.controllers.RentalBillController.class);
        resources.add(com.sabonay.advantageservices.controllers.StaffController.class);
        resources.add(com.sabonay.advantageservices.controllers.ZoneController.class);
        resources.add(com.sabonay.advantageservices.securities.CORSFilter.class);
    }

}
