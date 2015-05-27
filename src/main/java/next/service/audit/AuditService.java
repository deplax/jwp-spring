package next.service.audit;

import javax.annotation.Resource;

import next.dao.audit.AuditDao;
import next.model.audit.AuditObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {
	@Resource(name = "auditDao")
	private AuditDao auditDao;
	
	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void log(AuditObject audit) {
		auditDao.log(audit);
	}
}

//propagation - 전파옵션 (기본값 : REQUIRED)
//REQUIRED : 부모 트랜잭션 내에서 실행하며 부모 트랜잭션이 없을 경우 새로운 트랜잭션을 생성합니다.
//REQUIRES_NEW : 부모 트랜잭션을 무시하고 무조건 새로운 트랜잭션이 생성되도록 합니다.
//SUPPORT : 부모 트랜잭션 내에서 실행하며 부모 트랜잭션이 없을 경우 nontransactionally로 실행됩니다.
//MANDATORY : 부모 트랜잭션 내에서 실행되며 부모 트랜잭션이 없을 경우 예외가 발생됩니다.
//NOT_SUPPORT : nontransactionally로 실행하며 부모 트랜잭션 내에서 실행될 경우 일시 정지 됩니다.
//NEVER : nontransactionally로 실행되며 부모 트랜잭션이 존재한다면 예외가 발생합니다.
//NESTED : 해당 메서드가 부모 트랜잭션에서 진행될 경우 별개로 커밋되거나 롤백될 수 있습니다. 둘러싼 트랜잭션이 없을 경우 REQUIRED와 동일하게 작동합니다.
