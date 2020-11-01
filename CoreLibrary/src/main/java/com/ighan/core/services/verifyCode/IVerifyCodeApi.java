package com.ighan.core.services.verifyCode;

import com.ighan.core.services.common.models.ApiResult;
import com.ighan.core.services.verifyCode.models.VerifyCodeModel;
import com.ighan.core.services.verifyCode.models.VerifyCodeResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IVerifyCodeApi {

    @POST
    Observable<ApiResult<VerifyCodeResult>> verify(@Body VerifyCodeModel model);
}
