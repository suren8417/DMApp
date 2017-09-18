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
import java.util.Date;
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
            AuditResponseDto auditResponseDto = new AuditResponseDto();
            ObjectMapper mapper = new ObjectMapper();
            AuditQueryDto auditQueryDto = mapper.readValue(searchQueryString, AuditQueryDto.class);

            Long itemId = null;
            Long collectionId = null;
            String userName = null;
            Date startDate = null;
            Date endDate = null;

            if (auditQueryDto.getItem() != null) {
                itemId = Long.parseLong(auditQueryDto.getItem());
            }
            if (auditQueryDto.getCollection() != null) {
                collectionId = Long.parseLong(auditQueryDto.getCollection());
            }
            if (auditQueryDto.getUserName() != null) {
                userName = auditQueryDto.getUserName();
            }
            if (auditQueryDto.getStartDate() != null) {
                startDate = auditQueryDto.getStartDate();
            }
            if (auditQueryDto.getEndDate() != null) {
                endDate = auditQueryDto.getEndDate();
            }

            if ("user".endsWith(auditQueryDto.getSelectedSearch())) {
                UserAuditDto userAuditDto = auditService.getUserAuditInfo(userName, null, startDate, endDate);
                auditResponseDto.setAuditItemDtos(arrangeAuditItem(userAuditDto.getItemDtos()));
                auditResponseDto.setAuditCollectionDtos(arrangeAuditCollection(userAuditDto.getCollectionDtos()));
                return new ResponseEntity(auditResponseDto, HttpStatus.OK);
            } else if ("item".endsWith(auditQueryDto.getSelectedSearch())) {
                List<ItemDto> itemDtos = auditService.getItemAuditInfo(itemId, userName, null, startDate, endDate);
                auditResponseDto.setAuditItemDtos(arrangeAuditItem(itemDtos));
                return new ResponseEntity(auditResponseDto, HttpStatus.OK);
            } else if ("collection".endsWith(auditQueryDto.getSelectedSearch())) {
                List<CollectionDto> collectionDtos = auditService.getCollectionAuditInfo(collectionId, userName, null, startDate, endDate);
                auditResponseDto.setAuditCollectionDtos(arrangeAuditCollection(collectionDtos));
                return new ResponseEntity(auditResponseDto, HttpStatus.OK);
            }
            return null;
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    private List<AuditCollectionDto> arrangeAuditCollection(List<CollectionDto> collectionDtos) {

        List<AuditCollectionDto> auditCollectionDtos = new ArrayList<>();
        if (collectionDtos != null) {
            for (CollectionDto collectionDto : collectionDtos) {
                if (collectionDto.getAuditInfo() != null) {
                    AuditCollectionDto auditCollectionDto = new AuditCollectionDto();
                    auditCollectionDto.setName(collectionDto.getName());
                    auditCollectionDto.setDescription(collectionDto.getDescription());
                    if (collectionDto.getAuditInfo() != null) {
                        auditCollectionDto.setAuditorName(collectionDto.getAuditInfo().getAuditorName());
                        auditCollectionDto.setAuditTime(df.format(collectionDto.getAuditInfo().getAuditTime()));
                        auditCollectionDto.setOperation(collectionDto.getAuditInfo().getType());
                    }
                    auditCollectionDtos.add(auditCollectionDto);
                }
            }
        }
        return auditCollectionDtos;

    }


    private List<AuditItemDto> arrangeAuditItem(List<ItemDto> itemDtos) {

        List<AuditItemDto> auditItemDtos = new ArrayList<>();
        if (itemDtos != null) {
            for (ItemDto itemDto : itemDtos) {
                if (itemDto.getAuditInfo() != null) {
                    AuditItemDto auditItemDto = new AuditItemDto();
                    auditItemDto.setItemCode(itemDto.getItemCode());
                    auditItemDto.setType(itemDto.getItemsSelectedType());
                    auditItemDto.setTitle(itemDto.getItemTitle());
                    if(itemDto.getAddedDate() != null){
                        auditItemDto.setDateAdded(df.format(itemDto.getAddedDate()));
                    }
                    auditItemDto.setAddedBy(itemDto.getAddedBy());
                    if(itemDto.getValidatedDate() != null) {
                        auditItemDto.setDateValidated(df.format(itemDto.getValidatedDate()));
                    }
                    auditItemDto.setDonor(itemDto.getItemDonor());
                    auditItemDto.setDescription(itemDto.getItemDescription());
                    auditItemDto.setKeywords(itemDto.getItemKeyWords());
                    auditItemDto.setNote(itemDto.getItemNote());
                    if (itemDto.getAuditInfo() != null) {
                        auditItemDto.setAuditorName(itemDto.getAuditInfo().getAuditorName());
                        auditItemDto.setAuditTime(df.format(itemDto.getAuditInfo().getAuditTime()));
                        auditItemDto.setOperation(itemDto.getAuditInfo().getType());
                    }
                    auditItemDtos.add(auditItemDto);
                }
            }
        }
        return auditItemDtos;
    }
}