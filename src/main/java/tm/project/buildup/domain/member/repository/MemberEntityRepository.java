package tm.project.buildup.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.project.buildup.domain.member.entity.Member;

@Repository
public interface MemberEntityRepository extends JpaRepository<Member, Long> {

}
