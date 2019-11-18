package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

// a line --------------------
// you how enter here, abandon all hope.
@Service
@RequiredArgsConstructor
public class LdapUserAdapter {
    private final LdapUserWebserviceClient wsClient;

    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream().map(this::convertUser).collect(Collectors.toList());
    }

    private User convertUser(LdapUser ldapUser) {
        String fullName = getFullName(ldapUser);
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    private String getFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }
}