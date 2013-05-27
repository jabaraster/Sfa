package jabara.sfa.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-27T21:37:04.427+0900")
@StaticMetamodel(EMember.class)
public class EMember_ extends EntityBase_ {
	public static volatile SingularAttribute<EMember, String> userId;
	public static volatile SingularAttribute<EMember, Boolean> administrator;
}
