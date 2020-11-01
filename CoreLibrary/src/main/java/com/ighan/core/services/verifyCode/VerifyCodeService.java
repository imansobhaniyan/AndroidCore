package com.ighan.core.services.verifyCode;

import android.content.Context;

import com.ighan.core.services.abstractions.ApiCallResult;
import com.ighan.core.services.abstractions.BaseService;
import com.ighan.core.services.common.models.ApiResult;
import com.ighan.core.services.verifyCode.models.VerifyCodeModel;
import com.ighan.core.services.verifyCode.models.VerifyCodeResult;
import com.ighan.core.storage.ApiSharedPreferences;

public abstract class VerifyCodeService extends BaseService<IVerifyCodeApi> {

    protected VerifyCodeService() {
        super(IVerifyCodeApi.class);
    }

    public void verify(Context context, String verifyCode, ApiCallResult<ApiResult<VerifyCodeResult>> apiCallResult) {
        doObserveAndSubscribe(getApi().verify(new VerifyCodeModel(verifyCode)), new ApiCallResult<ApiResult<VerifyCodeResult>>() {
            @Override
            public void onSuccess(ApiResult<VerifyCodeResult> result) {
                if (result != null && result.isSuccess() && result.getData() != null && result.getData().getToken() != null && result.getData().getToken().length() > 0) {
                    ApiSharedPreferences.getInstance(context).setToken(result.getData().getToken());
                    ApiSharedPreferences.getInstance(context).setRefreshToken(result.getData().getRefreshToken());
                    apiCallResult.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable error) {
                apiCallResult.onError(error);
            }
        });
    }
}
