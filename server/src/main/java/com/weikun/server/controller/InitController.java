package com.weikun.server.controller;

import com.weikun.server.service.InitServiceImpl;
import com.weikun.server.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2018/10/21
 * ˵˵���ܣ�
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/init"},name="��ʼ������")
@Api(value = "init",description = "��ʼ���������ݽӿ�")
public class InitController {
    @Autowired
    private InitServiceImpl service;

    @ApiOperation(value = "init",notes = "��ʼ����Ϣ")
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "��ʼ������"),
            @ApiResponse(code=200,message = "��ʼ���ɹ�")
    })

    @GetMapping(value = {"/init"})
    public ResponseEntity<Void> init( ){
        try{
            service.init();
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
