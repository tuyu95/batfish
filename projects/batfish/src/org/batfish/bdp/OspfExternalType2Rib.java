package org.batfish.bdp;

import org.batfish.datamodel.OspfExternalType2Route;

public class OspfExternalType2Rib extends AbstractRib<OspfExternalType2Route> {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   public OspfExternalType2Rib(VirtualRouter owner) {
      super(owner);
   }

   @Override
   public int comparePreference(OspfExternalType2Route lhs,
         OspfExternalType2Route rhs) {
      // reversed on purpose
      int costComparison = Integer.compare(rhs.getMetric(), lhs.getMetric());
      if (costComparison != 0) {
         return costComparison;
      }
      return Integer.compare(rhs.getCostToAdvertiser(),
            lhs.getCostToAdvertiser());
   }

   @Override
   public boolean mergeRoute(OspfExternalType2Route route) {
      String advertiser = route.getAdvertiser();
      if (_owner._c.getHostname().equals(advertiser)) {
         return false;
      }
      else {
         return super.mergeRoute(route);
      }
   }

}
