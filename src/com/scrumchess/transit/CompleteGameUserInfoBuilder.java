/**
 * 
 */
package com.scrumchess.transit;

import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.game.CompleteGameInfoBuilder;
import com.scrumchess.transit.user.CompositeUserIdentification;
import com.scrumchess.transit.user.MultiUser;
import com.scrumchess.transit.user.SimpleCompositeUserIdetification;
import com.scrumchess.transit.user.SimpleMultiUser;

/**
 * @author Matthew
 *
 */
public class CompleteGameUserInfoBuilder extends CompleteGameInfoBuilder {

	private String pseudo[] = new String[2];
	private String id[] = new String[2];
	private MultiUser multiUser;
	
	
	public void setPseudonym(int i, String pseudo){
		this.pseudo[i]=pseudo;
	}
	public void setId(int i, String id){
		this.id[i]=id;
	}
	
	
	public CompleteGameUserInfo build(){
		CompleteGameUserInfo ret = null;
		if (checkBuildable()){
			super.build();
			multiUser = buildMultiUser();
			ret = new SimpleCompleteGameUserInfo(super.state,super.moveList,super.gameIdentification,super.playerConfiguration,this.multiUser);
		}
		return ret;
	}
	
	
	private MultiUser buildMultiUser(){
		MultiUser ret=null;
		if (super.checkBuildable()){
			switch (super.playerConfiguration.getConfigurationValue()){
			case BOTH:
				ret = new SimpleMultiUser(getCUID(0),getCUID(1));
				break;
			case BLACK:
				ret = new SimpleMultiUser(getCUID(0));
				break;
			case WHITE:
				ret = new SimpleMultiUser(getCUID(1));
				break;
			default:
				ret=null;
				break;
			}
		}
		return ret;
	}
	
	// calls super and checks buildable to determine if the 
	public boolean checkBuildable(){
		boolean ret = false;
		if (super.checkBuildable()){
			switch (super.playerConfiguration.getConfigurationValue()){
			case BOTH:
				ret = id[0]!=null && id[1]!=null;
				break;
			case BLACK:
				ret = id[0]!=null;
				break;
			case WHITE:
				ret = id[0]!=null;
				break;
			default:
				ret=true;
				break;
			}
		}
		return ret;
	}
	
	// checks the id has been set for a particular used
	private boolean checkId(int index){
		return (id[index]!=null);
	}
	
	// helper function
	private CompositeUserIdentification getCUID(int index){
		if(id[index]!=null && pseudo[index]!=null)
			return new SimpleCompositeUserIdetification(id[index],pseudo[index]);
		else if (id[index]!=null)
			return  new SimpleCompositeUserIdetification(id[index]);
		else
			return null;
	}
	
}
