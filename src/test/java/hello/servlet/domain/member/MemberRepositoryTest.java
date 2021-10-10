package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("jeon", 28);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member result = memberRepository.findById(savedMember.getId());
        assertThat(member).isSameAs(result);
    }

    @Test
    void findById() {
        //given
        Member member = new Member("jeon", 28);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member result = memberRepository.findById(savedMember.getId());
        assertThat(member.getId()).isEqualTo(result.getId());
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 25);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        //then
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

    @Test
    void clearStore() {
        //given
        Member member = new Member("member", 20);

        //when
        memberRepository.save(member);
        memberRepository.clearStore();

        //then
        List<Member> result2 = memberRepository.findAll();
        assertThat(result2.size()).isEqualTo(0);
    }
}