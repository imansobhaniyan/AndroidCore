package com.ighan.core.services.refreshToken;

import com.ighan.core.services.common.models.ApiResult;
import com.ighan.core.services.refreshToken.models.RefreshTokenModel;
import com.ighan.core.services.refreshToken.models.RefreshTokenResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRefreshTokenApi {

    @POST("/api/refreshToken")
    Observable<ApiResult<RefreshTokenResult>> refreshToken(@Body RefreshTokenModel model);
}
