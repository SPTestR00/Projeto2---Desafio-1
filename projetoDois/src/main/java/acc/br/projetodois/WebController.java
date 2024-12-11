package acc.br.projetodois;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class WebController {
	
	int vi=0, de=0, em=0;
	
	@Autowired
	public ScoreRepository scoreRepo;
	

	@ResponseBody
	@GetMapping("/score")
	public Score getScore() {
		Score score;
		try {
			score = scoreRepo.findById(new Integer(1)).get();
		}

		catch (Exception e) {
			score = new Score (0,0,0);
			scoreRepo.save(score);
		}
		 return score;
		 
	}
	

	@GetMapping ("/teste")
	public String teste(@RequestParam(name="escolha")String aEscolha, Model model) {
		String saida = "Empate";
		Score score;
		
		try {
			score = scoreRepo.findById(new Integer(1)).get();
		}
		catch (Exception e) {
			score = new Score (0,0,0);
			scoreRepo.save(score);
		}
		
		if (aEscolha.equalsIgnoreCase("papel")) {
			saida = "Ganhou!";
			vi++;
			score = scoreRepo.findById(1).get();
			score.setVitorias(vi);
			scoreRepo.save(score);
		}
		if (aEscolha.equalsIgnoreCase("tesoura")) {
			saida = "Perdeu!";
			de++;
			score = scoreRepo.findById(1).get();
			score.setDerrotas(de);
			scoreRepo.save(score);
			
		}
		if (aEscolha.equalsIgnoreCase("pedra")) {
			saida = "Empate!";
			em++;
			score = scoreRepo.findById(1).get();
			score.setEmpates(em);
			scoreRepo.save(score);
			
		}
		if (aEscolha.equalsIgnoreCase("zerar")) {
			saida = "Score foi zerado!";
			score = scoreRepo.findById(1).get();
			vi = 0; em = 0; de = 0;
			score.setEmpates(em);
			score.setDerrotas(de);
			score.setVitorias(vi);
			scoreRepo.save(score);
			
		}
		model.addAttribute("saida", saida);
		model.addAttribute("aEscolha", aEscolha);
		model.addAttribute("vi", vi);
		model.addAttribute("de", de);
		model.addAttribute("em", em);
		return "resultado";
	}
	
	
}
