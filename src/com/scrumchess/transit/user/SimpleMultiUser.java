package com.scrumchess.transit.user;

public class SimpleMultiUser extends SimpleCompositeUserIdetification implements MultiUser {
	
	private CompositeUserIdentification cuids[] = new CompositeUserIdentification[2];
	
	SimpleMultiUser(String id) {
		super(id);
		cuids[0] = this;
		init2nd(id);
	}
	SimpleMultiUser(String id, String pseudo){
		super(id,pseudo);
		cuids[0] = this;
	}
	public SimpleMultiUser(CompositeUserIdentification first, CompositeUserIdentification second){
		super(first);
		cuids[0] = this;
		init2nd(second);
	}
	public SimpleMultiUser(CompositeUserIdentification first){
		super(first);
		cuids[0]=this;
	}
	private void init2nd(CompositeUserIdentification compositeUserIdentification){
		cuids[1] = new SimpleCompositeUserIdetification(compositeUserIdentification);
	}
	private void init2nd(String id, String pseudo){
		
		cuids[1] = new SimpleCompositeUserIdetification(id,pseudo);
	}
	private void init2nd(String id){
		cuids[1] = new SimpleCompositeUserIdetification(id);
	}
	
	@Override
	public String getPseudonym(int index) {
		// TODO Auto-generated method stub
		return cuids[index].getPseudonym();
	}

	@Override
	public boolean hasPseudonym(int index) {
		// TODO Auto-generated method stub
		return cuids[index].hasPseudonym();
	}

	@Override
	public String getId(int index) {
		// TODO Auto-generated method stub
		return cuids[index].getId();
	}


	
}
