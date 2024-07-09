package controller;




import java.util.logging.LogManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;
import dto.TransactionDto;
import dto.TransferDto;
import service.TransactionService;


@Controller
@RequestMapping("/transaction")
@PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
public class TransactionController {

    private static final Logger logger = LogManager.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute TransactionDto transactionDto, RedirectAttributes redirectAttributes) {
        try {
            transactionService.deposit(transactionDto);
            redirectAttributes.addFlashAttribute("success", "Depósito exitoso");
            return "redirect:/dashboard";
        } catch (Exception e) {
            logger.error("Error depositing", e);
            redirectAttributes.addFlashAttribute("error", "Depósito fallido");
            return "redirect:/dashboard";
        }
    }

    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute TransactionDto transactionDto, RedirectAttributes redirectAttributes) {
        try {
            transactionService.withdraw(transactionDto);
            redirectAttributes.addFlashAttribute("success", "Retiro exitoso");
            return "redirect:/dashboard";
        } catch (Exception e) {
            logger.error("Error withdrawing", e);
            redirectAttributes.addFlashAttribute("error", "Retiro fallido: " + e.getMessage());
            return "redirect:/dashboard";
        }
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute TransferDto transferDto, RedirectAttributes redirectAttributes) {
        try {
            transactionService.transfer(transferDto);
            redirectAttributes.addFlashAttribute("success", "Transferencia exitosa");
            return "redirect:/dashboard";
        } catch (Exception e) {
            logger.error("Error transferring", e);
            redirectAttributes.addFlashAttribute("error", "Transferencia fallida: " + e.getMessage());
            redirectAttributes.addFlashAttribute("transferDto", transferDto);
            return "redirect:/dashboard";
        }
    }

}