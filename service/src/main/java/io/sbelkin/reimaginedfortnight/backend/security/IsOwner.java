//package io.elixir.backend.security;
//
//import com.yahoo.elide.security.ChangeSpec;
//import com.yahoo.elide.security.RequestScope;
//import com.yahoo.elide.security.checks.OperationCheck;
//import io.elixir.backend.core.User;
//
//import java.util.Optional;
//
///**
// * Created by sbelkin on 11/8/2016.
// */
//public class IsOwner extends OperationCheck<User> {
//    boolean ok(User user, RequestScope requestScope, Optional<ChangeSpec> changeSpec) {
//        return user.equals(requestScope.getUser().getOpaqueUser());
//    }
//
//    @Override
//    public boolean ok(User user, RequestScope requestScope, java.util.Optional<ChangeSpec> optional) {
//        return false;
//    }
//}