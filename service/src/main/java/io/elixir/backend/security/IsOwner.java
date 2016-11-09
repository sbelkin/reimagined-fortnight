//package io.elixir.backend.security;
//
//import com.google.common.base.Optional;
//import com.yahoo.elide.security.ChangeSpec;
//import com.yahoo.elide.security.RequestScope;
//
///**
// * Created by sbelkin on 11/8/2016.
// */
//public class IsOwner {
//    public static class Inline<History> extends InlineCheck {
//        @Override
//        boolean ok(History history, RequestScope requestScope, Optional<ChangeSpec> changeSpec) {
//            return history.account.equals(requestScope.getUser());
//        }
//    }
//}