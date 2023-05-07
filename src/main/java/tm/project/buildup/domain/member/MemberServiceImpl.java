package tm.project.buildup.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tm.project.buildup.domain.member.entity.Member;
import tm.project.buildup.domain.member.repository.MemberEntityRepository;
import tm.project.buildup.global.common.exception.BaseException;


import static tm.project.buildup.global.common.api.ResponseStatus.NOT_FIND_USER;
import static tm.project.buildup.global.common.entity.BaseEntity.State.ACTIVE;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberEntityRepository memberEntityRepository;
    @Override
    public Member getMember(Long id) {
        Member member = memberEntityRepository.findByIdAndState(id,ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_USER));;
        return member;
    }
}
