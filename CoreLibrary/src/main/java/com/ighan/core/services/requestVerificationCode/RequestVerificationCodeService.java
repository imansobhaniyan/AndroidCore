package com.ighan.core.services.requestVerificationCode;

import com.ighan.core.services.abstractions.ApiCallResult;
import com.ighan.core.services.abstractions.BaseService;
import com.ighan.core.services.common.models.ApiResult;
import com.ighan.core.services.requestVerificationCode.models.RequestVerificationCodeModel;
import com.ighan.core.services.requestVerificationCode.models.RequestVerificationCodeResult;

public abstract class RequestVerificationCodeService extends BaseService<IRequestVerificationCodeApi> {

    protected RequestVerificationCodeService() {
        super(IRequestVerificationCodeApi.class);
    }

    public void requestCode(String phoneNumber, ApiCallResult<ApiResult<RequestVerificationCodeResult>> apiCallResult){
        RequestVerificationCodeModel model = new RequestVerificationCodeModel(phoneNumber);
        doObserveAndSubscribe(getApi().requestCode(model), apiCallResult);
    }
}
