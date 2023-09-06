package com.camping101.beta.web.domain.camp.service;

import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.campauth.CampAuth;
import com.camping101.beta.db.entity.member.Member;
import com.camping101.beta.db.entity.site.Site;
import com.camping101.beta.web.domain.admin.campauth.repository.CampAuthRepository;
import com.camping101.beta.web.domain.camp.model.rq.CreateCampRq;
import com.camping101.beta.web.domain.camp.model.rq.ModifyCampRq;
import com.camping101.beta.web.domain.camp.repository.CampRepository;
import com.camping101.beta.web.domain.member.service.FindMemberService;
import com.camping101.beta.web.domain.site.service.FindSiteService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CampService {

    private final CampRepository campRepository;
    private final FindMemberService findMemberService;
    private final FindSiteService findSiteService;
    private final FindCampService findCampService;
    private final CampAuthRepository campAuthRepository;

    public Long saveCamp(CreateCampRq rq) {

        Camp camp = rq.toEntity();
        Camp savedCamp = campRepository.save(camp);

        Member findMember = findMemberService.findMemberOrElseThrow(rq.getMemberId());
        findMember.addCamp(savedCamp);

        callAdminister(savedCamp);

        return savedCamp.getCampId();
    }

    private void callAdminister(Camp camp) {
        CampAuth campAuth = camp.requestAuthorization();
        campAuthRepository.save(campAuth);
    }

    public void modifyCamp(ModifyCampRq rq) {

        Camp camp = findCampService.findCampOrElseThrow(rq.getCampId());
        rq.modify(camp);
    }

    public void removeCamp(Long campId) {

        Camp camp = findCampService.findCampOrElseThrow(campId);

        List<Long> siteIds = camp.getSites().stream().map(Site::getSiteId)
            .collect(Collectors.toList());

        for (Long siteId : siteIds) {
            Site site = findSiteService.findSiteOrElseThrow(siteId);
            site.hasReservation();
        }

        campRepository.delete(camp);
    }
}
