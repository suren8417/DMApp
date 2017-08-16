package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.services.AuditService;
import com.tc.dm.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suren on 13/08/17.
 */
@Controller
@RequestMapping("/audits")
public class AuditController {

DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");

private AuditService auditService;

    @Autowired
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItems(@RequestParam("searchQuery") String searchQueryString) {

        try {
            List<AuditResponseDto> auditResponseDtos = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            AuditQueryDto auditQueryDto = mapper.readValue(searchQueryString, AuditQueryDto.class);
            if(auditQueryDto.getUserName() != null && auditQueryDto.getUserName() !=""){
                UserAuditDto  userAuditDto= auditService.getUserAuditInfo(auditQueryDto.getUserName(), null, null, null);

                for(ItemDto itemDto : userAuditDto.getItemDtos()){
                    if(itemDto.getAuditInfo() != null) {
                        AuditResponseDto auditResponseDto = new AuditResponseDto();
                        auditResponseDto.setName(itemDto.getItemTitle());
                        auditResponseDto.setAuditorName(itemDto.getAuditInfo().getAuditorName());;
                        auditResponseDto.setAuditTime(df.format(itemDto.getAuditInfo().getAuditTime()));
                        auditResponseDto.setOperation(itemDto.getAuditInfo().getType().toUpperCase());
                        auditResponseDto.setType("ITEM");
                        auditResponseDtos.add(auditResponseDto);
                    }
                }

                for(CollectionDto collectionDto : userAuditDto.getCollectionDtos()){
                    if(collectionDto.getAuditInfo() != null) {
                        AuditResponseDto auditResponseDto = new AuditResponseDto();
                        auditResponseDto.setName(collectionDto.getName());
                        auditResponseDto.setAuditorName(collectionDto.getAuditInfo().getAuditorName());
                        auditResponseDto.setAuditTime(df.format(collectionDto.getAuditInfo().getAuditTime()));
                        auditResponseDto.setOperation(collectionDto.getAuditInfo().getType().toUpperCase());
                        auditResponseDto.setType("COLLECTION");
                        auditResponseDtos.add(auditResponseDto);
                    }
                }


            }else{

                if(auditQueryDto.getItem() != null && auditQueryDto.getItem() !=""){
                    //UserAuditDto  userAuditDto= auditService.getCollectionAuditInfo();
                }
                if(auditQueryDto.getCollection() != null && auditQueryDto.getCollection() !=""){
                    //UserAuditDto  userAuditDto= auditService.getCollectionAuditInfo();
                }
            }

            return new ResponseEntity(auditResponseDtos, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
