package controller;



import service.BankAccountService;
import service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.BankAccount;
import model.Transactions;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger logger = LogManager.getLogger(DashboardController.class);

    private final BankAccountService bankAccountService;
    private final TransactionService transactionService;

    public DashboardController(BankAccountService bankAccountService, TransactionService transactionService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
    }

    @RequestMapping
    public String dashboard(Model model, HttpServletRequest request) {

        // Obtener de la autenticación el email del usuario
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        // Buscar la cuenta bancaria del usuario
        Optional<BankAccount> bankAccountOptional = bankAccountService.findBankAccountByUserEmail(userEmail);
        Collection<Transactions> transactions = transactionService.getTransactionsByUserEmail(userEmail);

        if (bankAccountOptional.isEmpty()) {
            logger.error("No se encontró la cuenta bancaria del usuario con email: {}", userEmail);
            return "redirect:/";
        }

        if (request.getSession().getAttribute("loginSuccess") != null) {
            model.addAttribute("loginSuccess", request.getSession().getAttribute("loginSuccess"));
            request.getSession().removeAttribute("loginSuccess");
        }

        BankAccount bankAccount = bankAccountOptional.get();

        model.addAttribute("bankAccount", bankAccount);
        model.addAttribute("transactions", transactions);

        return "views/privates/dashboard";
    }
}
