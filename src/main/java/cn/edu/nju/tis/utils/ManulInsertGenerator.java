package cn.edu.nju.tis.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * @ClassName ManulInsertGenerator
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/5/10 6:52 PM
 * @Version 1.0
 **/
public class ManulInsertGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) throws HibernateException {
        Serializable id = s.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, s);

        if (id != null && Integer.valueOf(id.toString()) > 0) {
            return id;
        } else {
            return super.generate(s, obj);
        }
    }
}
