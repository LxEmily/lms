package ie.ucd.lms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ie.ucd.lms.dao.MemberRepository;
import ie.ucd.lms.entity.Login;
import ie.ucd.lms.entity.Member;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	public void save(Member member, Login login) {
		login.setMember(member);
		member.setLogin(login);

		memberRepository.save(member);
	}

	public Page<Member> search(String stringToSearch, int pageNum) {
		Long id = Common.convertStringToLong(stringToSearch);

		Page<Member> res = memberRepository
				.findByIdOrFullNameContainsIgnoreCaseOrEmailContainsIgnoreCaseOrMobileNumberContainsOrAddressContainsIgnoreCaseOrTypeIgnoreCaseContains(
						id, stringToSearch, stringToSearch, stringToSearch, stringToSearch, stringToSearch,
						PageRequest.of(pageNum, Common.PAGINATION_ROWS));
		return res;
	}

	public Boolean update() {
		return false;
	}

	public Boolean isMember(String stringToSearch) {
		return !isLibrarian(stringToSearch);
	}

	public Boolean isLibrarian(String stringToSearch) {
		Long id = Common.convertStringToLong(stringToSearch);

		Optional<Member> member = memberRepository.findById(id);
		if (member.isPresent()) {
			if (member.get().getType().toLowerCase().equals("librarian")) {
				return true;
			}
		}
		return false;
	}

	public Optional<Member> findById(Long id) {
		return memberRepository.findById(id);
	}

	public Member createMember(Login login) {
		Member member = new Member();
		member.setEmail(login.getEmail());

		return member;
	}

	public Member findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	public void printMe(List<Member> arr) {
		System.out.println("\n\nPrinting search result:");
		for (Member member : arr) {
			System.out.println(member);
		}
		;
	}

	public void printAll() {
		System.out.println("\n\nPrinting all:");
		for (Member member : memberRepository.findAll()) {
			System.out.println(member);
		}
		;
	}

	public void printAllWithLoanHistory() {
		System.out.println("\n\nPrinting search result:");
		for (Member member : memberRepository.findAll()) {
			System.out.println(member.toStringWithLoanHistory());
		}
		;
	}

	public void printAllWithReserveQueue() {
		System.out.println("\n\nPrinting search result:");
		for (Member member : memberRepository.findAll()) {
			System.out.println(member.toStringWithReserveQueue());
		}
		;
	}
}