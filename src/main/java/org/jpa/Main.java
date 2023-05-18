package org.jpa;

import org.jpa.diomain.Member;
import org.jpa.diomain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team("TeamA");
            em.persist(team);

            Member member = new Member("bellCold", team);
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==============");
            List<Member> memberList = team.getMemberList();
            for (Member m : memberList) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("==============");

            String name = member.getTeam().getName();
            System.out.println("name = " + name);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}