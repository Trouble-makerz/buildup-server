package tm.project.buildup.global.common.config.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tm.project.buildup.domain.member.entity.Member;
import tm.project.buildup.domain.member.repository.MemberEntityRepository;
import tm.project.buildup.global.common.api.ErrorCode;
import tm.project.buildup.global.common.exception.BaseException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberEntityRepository memberEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberEntityRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        return User.withUsername(username)
                .password(member.getId().toString())
                .authorities(AuthorityUtils.NO_AUTHORITIES)
                .build();
    }
}