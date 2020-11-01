package com.ighan.core.services.requestVerificationCode;

import com.ighan.core.services.common.models.ApiResult;
import com.ighan.core.services.requestVerificationCode.models.RequestVerificationCodeModel;
import com.ighan.core.services.requestVerificationCode.models.RequestVerificationCodeResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRequestVerificationCodeApi {

    @POST
    Observable<ApiResult<RequestVerificationCodeResult>> requestCode(@Body RequestVerificationCodeModel model);
}
