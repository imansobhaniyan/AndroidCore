package com.ighan.core.services.refreshToken;

import android.content.Context;

import com.ighan.core.services.abstractions.ApiCallResult;
import com.ighan.core.services.abstractions.BaseService;
import com.ighan.core.services.common.models.ApiResult;
import com.ighan.core.services.refreshToken.models.RefreshTokenModel;
import com.ighan.core.services.refreshToken.models.RefreshTokenResult;
import com.ighan.core.storage.ApiSharedPreferences;

public abstract class RefreshTokenService extends BaseService<IRefreshTokenApi> {

    protected RefreshTokenService() {
        super(IRefreshTokenApi.class);
    }

    public void refreshToken(Context context, ApiCallResult<ApiResult<RefreshTokenResult>> apiCallResult) {
        String refreshToken = ApiSharedPreferences.getInstance(context).getRefreshToken();
        doObserveAndSubscribe(getApi().refreshToken(new RefreshTokenModel(refreshToken)), new ApiCallResult<ApiResult<RefreshTokenResult>>() {
            @Override
            public void onSuccess(ApiResult<RefreshTokenResult> result) {
                if (result != null && result.isSuccess() && result.getData() != null && result.getData().getToken() != null && result.getData().getToken().length() > 0) {
                    ApiSharedPreferences.getInstance(context).setToken(result.getData().getToken());
                    ApiSharedPreferences.getInstance(context).setRefreshToken(result.getData().getRefreshToken());
                }
                apiCallResult.onSuccess(result);
            }

            @Override
            public void onError(Throwable error) {
                apiCallResult.onError(error);
            }
        });
    }
}
