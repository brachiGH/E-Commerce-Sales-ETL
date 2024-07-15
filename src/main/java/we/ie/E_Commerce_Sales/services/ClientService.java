package we.ie.E_Commerce_Sales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import we.ie.E_Commerce_Sales.repositories.ClientRepository;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    public void consulter() {
			clientRepository.findAll().forEach( clt -> {
				System.out.println(clt.getName());
			});
    }

}
