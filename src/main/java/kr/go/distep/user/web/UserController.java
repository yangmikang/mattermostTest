package kr.go.distep.user.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.go.distep.user.service.UserService;
import kr.go.distep.user.vo.UserVO;

@Controller
public class UserController {

	/** logger */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "passwordEncoder")
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/userRegister.do", method = RequestMethod.GET)
	public ModelAndView index(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {

		ModelAndView view = new ModelAndView();

		String referer = (String) request.getHeader("REFERER");

		view.addObject("prevURL", referer);
		view.setViewName("user/register");

		return view;
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String register(UserVO userVO, RedirectAttributes redirectAttributes) throws IOException, Exception {
		
		String rawPassword = userVO.getPassword();
	    String encryptedPassword = passwordEncoder.encode(rawPassword);
	    userVO.setPassword(encryptedPassword);
	    
		userService.register(userVO);
		redirectAttributes.addFlashAttribute("successMessage", "Registration completed successfully.");
		return "redirect:/login.do";
	}
	
	@RequestMapping(value = "/login.do")
	public ModelAndView login(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {

		ModelAndView view = new ModelAndView();

		String referer = (String) request.getHeader("REFERER");

		view.addObject("prevURL", referer);
		view.setViewName("user/login");

		return view;
	}
	
	@RequestMapping(value = "/admin.do")
	public ModelAndView admin(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String activeTab, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {

		ModelAndView view = new ModelAndView();
		
		int totalCount = userService.getUserCount();
	    int offset = (page - 1) * size;
		List<UserVO> userList = userService.getUserList(offset, size);
		model.addAttribute("userList", userList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("offset", offset);
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("pageSize", size);
	    model.addAttribute("imgVersion", System.currentTimeMillis());
	    
		view.setViewName("user/admin");

		return view;
	}
	@RequestMapping(value = "/adminBoard.do")
	public ModelAndView adminBoard(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String activeTab, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		
		ModelAndView view = new ModelAndView();
		
		List<HashMap<String, Object>> boardPermissionList = userService.getBoardPermissionList();
		model.addAttribute("boardPermissionList", boardPermissionList);
		
		view.setViewName("user/adminBoard");
		
		return view;
	}
	
	@RequestMapping("/userLogin.do")
    public String login(@RequestParam("id") String userId,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) throws IOException, Exception {

        UserVO user = userService.findByUserId(userId);

        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User ID does not exist.");
            return "redirect:/login.do";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Incorrect password.");
            return "redirect:/login.do";
        }

        session.setAttribute("loggedInUser", user);
        session.setMaxInactiveInterval(1800);

        return "redirect:/index.do";
    }
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {

	    session.invalidate();

	    return "redirect:/index.do"; 
	}

	@RequestMapping(value = "/checkUserId.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserId(@RequestParam("userId") String userId) throws IOException, Exception {
		boolean chkYn = userService.checkUserId(userId);
		return chkYn ? "Y" : "N";
	}
	@RequestMapping(value = "/checkEmail.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(@RequestParam("email") String email) throws IOException, Exception {
		boolean chkYn = userService.checkEmail(email);
		return chkYn ? "Y" : "N";
	}
	@RequestMapping(value = "/checkUserName.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkUsername(@RequestParam("username") String username) throws IOException, Exception {
		boolean chkYn = userService.checkUsername(username);
		return chkYn ? "Y" : "N";
	}
	
	@RequestMapping(value = "/adminImgUpload.do", method = RequestMethod.POST)
	public String uploadMainImage(@RequestParam("imageFile") MultipartFile file, Model model, RedirectAttributes redirectAttributes) throws Exception {

	    if (!file.isEmpty()) {
	        String realPath = servletContext.getRealPath("/img/pattern/content/img_main_content_visual_1.png");
	        File dest = new File(realPath);
	        
	        if (isSameFile(file, dest)) {
	            redirectAttributes.addFlashAttribute("errorMessage", "The uploaded image is the same as the current main image.");
	        } else {
	            file.transferTo(dest);
	        }
	    }

	    return "redirect:/admin.do?activeTab=tab2";
	}
	
	@RequestMapping(value = "/updateUserRole.do", method = RequestMethod.POST)
	public String updateUserRole(@RequestParam("id") String id, @RequestParam("appr_yn") String appr_yn, @RequestParam(value = "page", defaultValue = "1") int page) {
        userService.updateUserRole(id, appr_yn);
	    return "redirect:/admin.do?page="+page;
	}
	
	@RequestMapping(value = "/updateUserType.do", method = RequestMethod.POST)
	public String updateUserType(@RequestParam("id") String id, @RequestParam("role") String role, @RequestParam(value = "page", defaultValue = "1") int page) {
		userService.updateUserType(id, role);
		return "redirect:/admin.do?page="+page;
	}
	
	@RequestMapping(value = "/grantAdminRole.do", method = RequestMethod.POST)
	public String grantAdminRole(@RequestParam("id") String id, @RequestParam(value = "page", defaultValue = "1") int page) {
        userService.grantAdminRole(id);
	    return "redirect:/admin.do?page="+page;
	}
	@RequestMapping(value = "/removeAdminRole.do", method = RequestMethod.POST)
	public String removeAdminRole(@RequestParam("id") String id, @RequestParam(value = "page", defaultValue = "1") int page) {
		userService.removeAdminRole(id);
		return "redirect:/admin.do?page="+page;
	}
	
	@RequestMapping(value = "/deleteUser.do", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("id") String id) {
        userService.deleteUser(id);
        return "redirect:/admin.do";
    }
	
	@RequestMapping(value = "/deleteAccount.do", method = RequestMethod.POST)
	public String deleteAccount(@RequestParam("id") String id) {
		userService.deleteUser(id);
		
	    return "redirect:/editUser.do"; 
	}
	
	@RequestMapping(value = "/adminUserPwReset.do", method = RequestMethod.POST)
	public String adminUserPwReset(@RequestParam("id") String id, @RequestParam(value = "page", defaultValue = "1") int page) {

		String tempPassword = "aaaa1111";
		
		String encryptedPassword = passwordEncoder.encode(tempPassword);
		userService.updateUserPassword(id, encryptedPassword);
		
		return "redirect:/admin.do?page="+page;
	}
	
	public boolean isSameFile(MultipartFile uploadFile, File savedFile) throws Exception {
	    byte[] uploadBytes = uploadFile.getBytes();
	    byte[] savedBytes = Files.readAllBytes(savedFile.toPath());

	    if (uploadBytes.length != savedBytes.length) {
	        return false;
	    }

	    for (int i = 0; i < uploadBytes.length; i++) {
	        if (uploadBytes[i] != savedBytes[i]) {
	            return false;
	        }
	    }
	    return true;
	}
	
	@RequestMapping(value = "/findId.do", method = RequestMethod.GET)
	public String findIdForm() {
	    return "user/findId";
	}

	@RequestMapping(value = "/findId.do", method = RequestMethod.POST)
	public String findId(@RequestParam("name") String name,
	                     @RequestParam("email") String email,
	                     Model model,
	                     RedirectAttributes redirectAttributes) {

	    String userId = userService.findUserIdByNameAndEmail(name, email);

	    if (userId != null) {
	        model.addAttribute("foundUserId", userId);
	        return "user/findIdResult";
	    } else {
	        redirectAttributes.addFlashAttribute("errorMessage", "No matching user found.");
	        return "redirect:/findId.do";
	    }
	}
	
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.GET)
	public String findPasswordForm() {
	    return "user/findPassword";
	}

	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public String findPassword(@RequestParam("userId") String userId,
	                           @RequestParam("email") String email,
	                           Model model,
	                           RedirectAttributes redirectAttributes) {

	    UserVO user = userService.findUserByIdAndEmail(userId, email);

	    if (user == null) {
	        redirectAttributes.addFlashAttribute("errorMessage", "No matching user found.");
	        return "redirect:/findPassword.do";
	    }

	    String tempPassword = generateTempPassword();
	    String encodedPassword = passwordEncoder.encode(tempPassword);

	    userService.updateUserPassword(userId, encodedPassword);

	    model.addAttribute("tempPassword", tempPassword);
	    return "user/tempPasswordResult";
	}
	
	public String generateTempPassword() {
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < 10; i++) {
	        int index = (int) (Math.random() * chars.length());
	        sb.append(chars.charAt(index));
	    }
	    return sb.toString();
	}
	
	@RequestMapping(value = "/editUser.do", method = RequestMethod.GET)
	public String editUser(HttpSession session, Model model) throws IOException, Exception {
	    UserVO loggedInUser = (UserVO) session.getAttribute("loggedInUser");

	    if (loggedInUser == null) {
	        return "redirect:/login.do";
	    }

	    UserVO user = userService.findByUserId(loggedInUser.getId());
	    model.addAttribute("user", user);

	    return "user/editUser"; 
	}

	@RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
	public String updateUser(UserVO userVO, RedirectAttributes redirectAttributes, HttpSession session) throws IOException, Exception {
	    userService.updateUser(userVO);

	    // 세션에 반영
	    session.setAttribute("loggedInUser", userService.findByUserId(userVO.getId()));
	    redirectAttributes.addFlashAttribute("successMessage", "User information updated successfully.");

	    return "redirect:/editUser.do";
	}

	@RequestMapping(value = "/changePassword.do", method = RequestMethod.GET)
	public String changePasswordForm() {
	    return "user/changePassword";
	}

	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String changePassword(@RequestParam("currentPassword") String currentPassword,
	                             @RequestParam("newPassword") String newPassword,
	                             HttpSession session,
	                             RedirectAttributes redirectAttributes) throws IOException, Exception {

	    UserVO user = (UserVO) session.getAttribute("loggedInUser");
	    if (user == null) {
	        return "redirect:/login.do";
	    }

	    UserVO dbUser = userService.findByUserId(user.getId());
	    if (!passwordEncoder.matches(currentPassword, dbUser.getPassword())) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Current password is incorrect.");
	        return "redirect:/changePassword.do";
	    }

	    String encodedPassword = passwordEncoder.encode(newPassword);
	    userService.updateUserPassword(user.getId(), encodedPassword);

	    redirectAttributes.addFlashAttribute("successMessage", "Password changed successfully.");
	    return "redirect:/editUser.do"; 
	}
	
	@RequestMapping(value = "/admin/updateAuth.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateBoardAuth(@RequestBody UserVO userVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.upsertBoardAuth(userVO);
            response.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
        }
        return response;
    }
	
	@RequestMapping(value ="/updateBoardMasterName.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateBoardMasterName(@RequestBody Map<String, String> paramMap) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        String boardMasterCode = paramMap.get("boardMasterCode");
	        String boardMasterName = paramMap.get("boardMasterName");

	        int updated = userService.updateBoardMasterName(boardMasterCode, boardMasterName);
	        result.put("success", updated > 0);
	    } catch (Exception e) {
	        result.put("success", false);
	        result.put("message", e.getMessage());
	    }
	    return result;
	}
	
	@RequestMapping(value ="/updateBoardUseYn.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateBoardUseYn(@RequestBody Map<String, String> paramMap) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        String boardMasterCode = paramMap.get("boardMasterCode");
	        String boardUseYn = paramMap.get("boardUseYn");

	        int updated = userService.updateBoardUseYn(boardMasterCode, boardUseYn);
	        result.put("success", updated > 0);
	    } catch (Exception e) {
	        result.put("success", false);
	        result.put("message", e.getMessage());
	    }
	    return result;
	}
	
}
