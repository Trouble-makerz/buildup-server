package tm.project.buildup.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.project.buildup.domain.member.entity.Member;
import static tm.project.buildup.global.common.entity.BaseEntity.State;
import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdAndState(Long id, State state);
}
