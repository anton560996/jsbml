//package org.sbml.jsbml.validator.offline.constraints;
//
//
//public class LayoutConstraintBuilder extends AbstractConstraintBuilder {
//
//    @Override
//    public AnyConstraint<?> createConstraint(int id) {
//	
//	logger.info("Create " + id);
//	if (id >= LAYOUT_20101) {
//	    return createLayoutConstraint(id);
//	}
//	return null;
//    }
//
//    public AnyConstraint<?> createLayoutConstraint(int id) {
//	ValidationFunction<Layout> f = null;
//
//	switch (id) {
//	case LAYOUT_20315:
//	    f = new ValidationFunction<Layout>() {
//
//		@Override
//		public boolean check(ValidationContext ctx, Layout l) {
//
//		    return l.isSetDimensions();
//		}
//	    };
//	    break;
//
//	default:
//	    break;
//	}
//
//	return new ValidationConstraint<Layout>(id, f);
//    }
//
//}
