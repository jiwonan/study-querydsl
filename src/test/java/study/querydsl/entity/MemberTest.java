package study.querydsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberTest {
    @Autowired
    EntityManager em;

    @Test
    public void testEntity() {
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 15, teamA);

        Member member3 = new Member("member3", 20, teamB);
        Member member4 = new Member("member4", 25, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // 초기화.
        em.flush();
        em.clear();

        // 확인.
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for(Member member : members) {
            System.out.println("member = " + member);
            System.out.println("-> member.team " + member.getTeam());
        }
    }
}