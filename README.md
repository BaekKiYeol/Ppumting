# Ppumting (Ppumping Dating) 🏋️‍♀️
+ 함께 운동하기를 원하는 사람들을 위한 매칭 만남 및 온라인 PT 서비스 제공 사이트
+ 2022.7.11 ~ 2022.7.15

## 팀프로젝트
 + 총 5명 ( [본인](https://github.com/BaekKiYeol), [한태우](https://github.com/workhan0918), [이태영](https://github.com/leetaeyoung123) , [박상규](https://github.com/parkSangGyu98), [이성호](https://github.com/LeeSeongHo7984) )
## 사용한 기술 및 환경
+ Window
+ eclipse
+ HTML/CSS3
+ Java
+ Servlet
+ Jsp
+ MySQL
+ Javascript
+ bootstrap

## 구현 기능
+ 사용자
  + 회원가입, 탈퇴, 로그인, 로그아웃, 개인정보 수정
  + 게시판 등록, 수정, 삭제, 조회
  + 포인트 계좌 생성, 포인트 조회, 충전, 차감
  + 쪽지 발송, 답장, 조회, 삭제
+ 트레이너
  + 회원가입, 로그인, 로그아웃, 조회
   
## 담당 기능
+ 트레이너 부분
  + 회원가입, 탈퇴, 로그인, 로그아웃, 개인정보 수정
  
## 주요 코드
### 사용자 부분
 + 개인정보 수정
   1. 수정 클릭 시 현재 로그인되어있는 아이디를 이용하여 고객 정보를 가져와 미리 화면에 띄워둡니다.
   2. 완료 클릭 시 빈칸이 없는지 유효성검사 후 정보들을 새로운 객체로 받아옵니다.
   3. 새로운 객체를 업데이트 시킵니다.
 
			UserUpdateServlet 일부

			protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");

			String userId = request.getParameter("userId");

			HttpSession session = request.getSession(false);
			session.setAttribute("userId", userId);
			response.sendRedirect("../home");


			String pw = request.getParameter("pw");
			String phone = request.getParameter("phone");
			String name = request.getParameter("name");
			String addr = request.getParameter("addr");

			List<String> errorMsgs = new ArrayList<>();

			if(pw == null || pw.length() == 0) {
				errorMsgs.add("비밀번호를 입력해주세요");
			}else if(name == null || name.length() == 0) {
				errorMsgs.add("이름을 입력해주세요");
			}else if(phone == null || phone.length() == 0) {
				errorMsgs.add("전화번호를 입력해주세요");
			}else if(addr == null || addr.length() == 0) {
				errorMsgs.add("주소를 입력해주세요");
			}

			User user = new User();
			user.setUserId(userId);
			user.setPw(pw);
			user.setName(name);
			user.setPhone(phone);
			user.setAddr(addr);

			userService.updateUser(user);
			request.setAttribute("user", user);
			}

+ 포인트 계좌 생성
  1. 회원가입시 자동으로 랜덤한 숫자의 포인트가 0인 계좌를 생성해 줍니다.
  2. 본인 계좌번호는 충전할 경우 확인할 수 있습니다.
  
			PointDao 일부

			public String createAccountNum(String userId) { 
			String sql = "INSERT INTO Point (point, accountNum, userId) VALUES (0, ?, ?)";

			String numStr = String.valueOf((int) (Math.random() * 1000000000));
			StringBuilder sb = new StringBuilder();
			sb.append(numStr.substring(0, 3));
			sb.append("-");
			sb.append(numStr.substring(3, 5));
			sb.append("-");
			sb.append(numStr.substring(5));

			String result = sb.toString();

			try {
				Connection con = datasource.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				try {
					if (pointdao.isValidUser(userId)) { 
						if( pointdao.checkAccountNum(userId) == null ) { //생성해주기
							stmt.setString(1, result);
							stmt.setString(2, userId);
							stmt.executeUpdate();
						}else { 
							result = null;
						}
					} else {
						result = null;
					}
				} finally {
					stmt.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
			}
	
+ 쪽지 삭제
  1. 받은 보관함에서 쪽지를 삭제하면 상대방의 보낸 쪽지함에서도 삭제가 되는 상황이 발생하여
     받는사람, 보낸사람의 DB를 각각 만들어서 해결하였습니다.
	
			Note.sql 일부

			CREATE TABLE RcvNotes (
			no			BIGINT		 	PRIMARY KEY	AUTO_INCREMENT,
			sentid 		VARCHAR(20) 	NOT NULL	DEFAULT	'',
			userId		VARCHAR(20)		NOT NULL	DEFAULT	'',
			title		VARCHAR(100)	NOT NULL 	DEFAULT '',
			msg			VARCHAR(200)	NOT NULL 	DEFAULT '',
			sentDate 	TIMESTAMP		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
			sendnote	BOOLEAN			NOT NULL	DEFAULT FALSE
			);

			CREATE TABLE SendNotes (
			no			BIGINT		 	PRIMARY KEY	AUTO_INCREMENT,
			userId 		VARCHAR(20) 	NOT NULL	DEFAULT	'',
			recvid		VARCHAR(20)		NOT NULL	DEFAULT	'',
			title		VARCHAR(100)	NOT NULL 	DEFAULT '',
			msg			VARCHAR(200)	NOT NULL 	DEFAULT '',
			sentDate 	TIMESTAMP		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
			sendnote	BOOLEAN			NOT NULL	DEFAULT TRUE
			);
    
+ 회원가입
  1. 입력한 정보들의 공백 여부를 확인합니다.
  2. 공백이 있다면 에러메시지를 리스트로 error.jsp에 넘겨 alert을 띄워줍니다.
  3. 공백이 없다면 새로운 User객체에 정보들을 저장한 뒤 DB에 객체를 저장합니다.
			
			UserServlet 일부

			protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");

			String userId = request.getParameter("userId");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String ssn = request.getParameter("ssn");
			String phone = request.getParameter("phone");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");

			List<String> errorMsgs = new ArrayList<>();
			if(userId == null || userId.length() == 0) {
				errorMsgs.add("id를 입력해주세요,");		
			}else if(pw == null || pw.length() == 0) {
				errorMsgs.add("비밀번호를 입력해주세요");
			}else if(name == null || name.length() == 0) {
				errorMsgs.add("이름을 입력해주세요");
			}else if(ssn == null || ssn.length() == 0) {
				errorMsgs.add("주민번호를 입력해주세요");
			}else if(phone == null || phone.length() == 0) {
				errorMsgs.add("전화번호를 입력해주세요");
			}else if(addr1 == null || addr1.length() == 0 || 
					addr2 == null || addr2.length() == 0) {
				errorMsgs.add("주소를 입력해주세요");
			}

			RequestDispatcher dispatcher = null;
			if(errorMsgs.size() > 0) {
				dispatcher = request.getRequestDispatcher("error.jsp");
				request.setAttribute("errorMsgs", errorMsgs);
				dispatcher.forward(request, response);
				return;
			}


			User user = new User();
			user.setUserId(userId);
			user.setPw(pw);
			user.setName(name);
			user.setSsn(ssn);
			user.setPhone(phone);
			user.setAddr(addr1+ " " + addr2);
			Userservice userService = new Userservice();
			userService.addUser(user);
			pointService.createAccountNum(userId);
			request.setAttribute("user", user);
			response.sendRedirect("../loginout/login");
			return;
			}	


+ 게시판 수정
  1. 게시판에서 조회되는 글 중에서 본인 글만 수정할 수 있어야 하는데 모든 글을 수정할 수 있는 이슈 발생
  2. 세션으로 현재 로그인 되어있는 아이디와 게시글 글쓴이 아이디를 비교하여 해결
  
  			SelectQnaSevlet 일부
  	
  			protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
			Qna qnas = new Qna();
			qnas = service.viewQna(request.getParameter("qnaNo"));
			HttpSession session = request.getSession(false);
			String userId = (String) session.getAttribute("userId");

			if(qnas.getUserId().equals(userId)) { //게시글 주인이라면
				request.setAttribute("qnas", qnas);	// "qnas"(jsp에서 뿌려주는 이름)는 키, qnas는 값
				request.setAttribute("check", true);
				request.getRequestDispatcher("selectNoQna.jsp").forward(request, response);
			}else {
				request.setAttribute("qnas", qnas);
				request.setAttribute("check", false);
				request.getRequestDispatcher("selectNoQna.jsp").forward(request, response);
			}
			}
			
			
			selectNoQna.jsp 일부
			
			<c:if test="${check == false}">
			<div
			style="width: 100%; height: 50px; display: flex; text-align: center; font-size: 30px; line-height: 38px;">
			<a class="addBu" onclick="back()" style="text-decoration: none;">뒤로가기</a>
			</div>
			</c:if>
			<c:if test="${check == true}">
			<form action="modifyQna.do" method="post">
				<!--  <button type="submit" value="${qnas.qnaNo}" name="qnaNo">수정</button> -->
				<button type="submit" value="${qnas.qnaNo}" name="qnaNo"
					onclick="location.href='modifyQna.jsp'" class="addBu">
					<span style="font-size: 30px; line-height: 38px;" class="ft">수정</span>
				</button>
			</form>
			<form action="deleteQna.do" method="post">
				<button type="submit" value="${qnas.qnaNo}" name="qnaNo"
					class="addBu">
					<span style="font-size: 30px; line-height: 38px;" class="ft">삭제</span>
				</button>
			</form>
			<div
				style="width: 100%; height: 50px; display: flex; text-align: center; font-size: 30px; line-height: 38px;">
				<a class="addBu" onclick="back()" style="text-decoration: none;">뒤로가기</a>
			</div>
			</c:if>
			

 ## 구현 화면
 ### 사용자, 트레이너 로그인

 ![image](https://user-images.githubusercontent.com/103983349/185534090-186fb9dd-14db-476a-99d6-767c419657e0.png)
 ![image](https://user-images.githubusercontent.com/103983349/185536667-5fd94a8f-6a27-4d2d-b691-b8d2d3af2e88.png)


 ### 사용자, 트레이너 회원가입
 
 ![image](https://user-images.githubusercontent.com/103983349/185534159-e81b5b53-f5f6-48bd-98bc-e588f7efb38d.png)
 ![image](https://user-images.githubusercontent.com/103983349/185536890-5a201228-3bdc-4ee6-86db-4ffbdbec62e6.png)
 
 ### 메인
 
 ![image](https://user-images.githubusercontent.com/103983349/185534293-83c2eee3-b87b-4329-8315-f73677eda0a8.png)
 
 ### 회원정보 수정
 
 ![image](https://user-images.githubusercontent.com/103983349/185534413-484fbfb8-d355-414c-8fb9-67dcdc1c69fa.png)

 
 ### 게시판
 
 ![image](https://user-images.githubusercontent.com/103983349/185874733-2b10934f-d014-4070-b492-d40070b78ec6.png)
 
 ### 게시글 등록
 
 ![image](https://user-images.githubusercontent.com/103983349/185874804-1c530035-1447-4870-acaf-c11654eaf3b6.png)
 
 ### 게시글 보기
 
 ![image](https://user-images.githubusercontent.com/103983349/185874881-3cb1a517-a515-4eb4-a204-a5b3be22baee.png)
 
 ### 게시글 수정
 
 ![image](https://user-images.githubusercontent.com/103983349/185874920-0d98e605-e7fa-409b-a39b-4ab157a7c575.png)
 
 ### P.P.T
 
 ![image](https://user-images.githubusercontent.com/103983349/185537275-85d5be18-0fe8-4f87-af95-578e83c5e30a.png)
 
 ### 충전
 
 ![image](https://user-images.githubusercontent.com/103983349/185534437-c0321692-2853-4600-8bd7-35ebeef06e5b.png)

