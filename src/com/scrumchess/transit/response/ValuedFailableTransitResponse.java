package com.scrumchess.transit.response;

import com.scrumchess.transit.game.CompleteGameInfo;

public interface ValuedFailableTransitResponse<T> extends FailReason, FailableRequest, ReturnableObjectContainer<T>, TransitResponse {

}
