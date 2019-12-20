package jun.views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jun.domain.subVO;
import jun.util.JDBCUtil;

public class MainController {
	@FXML
	private ListView<subVO> viewList;
	
	@FXML
	private ListView<String> menuList;
	
	@FXML
	private ListView<String> neList;
	
	@FXML
	private ComboBox<String> cBox;
	
	@FXML
	private ComboBox<String> dlcBox;
	
	@FXML
	private TextField txtNumber;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label label;
	
	private ObservableList<subVO> list; // db
	private ObservableList<String> jList; // 직업
	private ObservableList<String> dList; // 확장팩
	private ObservableList<String> mList; // 카드
	private ObservableList<String> nList; // 중립
	
	private String select;
	private String selec;
	
	private int num = 0;
	
	private boolean selectb = false;
	private boolean selecb = false;
	
	@FXML
	public void initialize() {
		String name = " 이름 : ";
		list = FXCollections.observableArrayList();
		viewList.setItems(list);
		
		jList = FXCollections.observableArrayList("전사", "성기사", "흑마법사", "주술사", "사냥꾼", "마법사", "도적", "사제", "드루이드");
		cBox.setItems(jList);
		
		dList = FXCollections.observableArrayList("오리지널" , "기본", "명예의 전당", "낙스라마스의 저주", "고블린 대 노움", "검은바위 산", "대 마상시합", "탐험가 연맹", "고대 신의 속삭임", "한여름 밤의 카라잔", "비열한 거리의 가젯잔", "운고로를 향한 여정", "얼어붙은 왕좌의 기사들", "코볼트와 지하 미궁", "마녀숲", "박사 붐의 폭심만만 프로젝트", "라스타칸의 대난투", "어둠의 반격", "울둠의 구원자");
		dlcBox.setItems(dList);
		
		nList = FXCollections.observableArrayList(name + "위습",name + "가혹한 하사관",name + "굶주린 게",name + "남쪽바다 갑판원",name + "늑대인간 침투요원",name + "멀록 파도술사",name + "방패병",name + "붉은해적단 바다사냥꾼",name + "비밀지기",name + "빛의 감시자",name + "어린 용매",name + "오염된 노움",name + "젊은 여사제",name + "은빛십자군 종자",name + "화난 닭",name + "검 제작의 대가",name + "고대의 감시자",name + "광기의 연금술사",name + "광기의 화염술사",name + "광포한 늑대 우두머리",name + "내트 페이클",name + "단검 곡예사",name + "마나 망령",name + "마나 중독자",name + "밀하우스 마나스톰",name + "붉은해적단 약탈자",name + "성난태양 파수병",name + "아마니 광전사",name + "요정용",name + "자그마한 소환사",name + "전리품 수집가",name + "전승지기 초",name + "젊은 양조사",name + "정신나간 폭격수",name + "파멸의 예언자",name + "혈법사 탈노스",name + "고통의 수행사제",name + "굶주린 식인 구울",name + "남쪽바다 선장",name + "노움 자동경보기",name + "대지 고리회 선견자",name + "멀록 전투대장",name + "무쇠부리 올빼미",name + "밀림 표범",name + "밀림의 왕 무클라",name + "부상당한 검귀",name + "붉은십자군 성전사",name + "비전골렘",name + "빛나래",name + "수석땜장이",name + "스랄마 선견자",name + "시린빛 예언자",name + "임프 소환사",name + "정신 지배 기술자",name + "퀘스트 중인 모험가",name + "타우렌 전사",name + "파괴전차",name + "허수아비골렘",name + "혈기사",name + "황제 코브라",name + "흉포한 늑대인간",name + "SI:7침투요원",name + "검은무쇠 드워프",name + "고대의 마법사",name + "고대의 양조사",name + "공포의 해적",name + "모구샨 감시자",name + "보랏빛 여교사",name + "실버문 수호병",name + "아르거스의 수호자",name + "이교도 지도자",name + "주문파괴자",name + "황혼의 비룡",name + "가시덤불 호랑이",name + "나 이런 사냥꾼이야",name + "날뛰는 코도",name + "누더기골렘",name + "리로이 젠킨스",name + "선장 그린스킨",name + "수렁이끼괴물",name + "얼굴 없눈 배후자",name + "원한 맺힌 대장장이",name + "은빛 성기사단 기사",name + "투자개발회 용병",name + "해리슨 존스",name + "가젯잔 경매인",name + "괴수",name + "냉기 정령",name + "들창코",name + "성난바람 하피",name + "엘룬의 여사제",name + "은빛십자군 부대장",name + "일리단 스톰레이지",name + "케른 블러드후프",name + "태양길잡이",name + "흑기사",name + "남작게돈",name + "라벤홀트 암살자",name + "불모의 땅 마구간지기",name + "종교재판관 화이트메인",name + "그룰",name + "비전 포식자",name + "노즈도르무",name + "말리고스",name + "알렉스트라자",name + "오닉시아",name + "이세라",name + "데스윙",name + "바다 거인",name + "산악 거인");
		neList.setItems(nList);
		
		 cBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observ, String old, String job) -> {
			 System.out.println(old + "->" + job);
			 // 직업 바뀔때마다 적용
			 if(job == "전사") {
				 jList.remove("성기사");
				 jList.remove("흑마법사");
				 jList.remove("주술사");
				 jList.remove("사냥꾼");
				 jList.remove("마법사");
				 jList.remove("도적");
				 jList.remove("사제");
				 jList.remove("드루이드");
				cBox.setItems(jList);
				dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					System.out.println(o + "->" + dlc);
					if(dlc == "오리지널") {
						mList = FXCollections.observableArrayList(name + "내면의 분노",name + "강화!",name + "방패 밀쳐내기",name + "격돌",name + "광란",name + "방어구 제작자",name + "잔인한 감독관",name + "전투 격노",name + "지휘의 외침",name + "거품 무는 광전사",name + "아라시 무기제작자",name + "필사의 일격",name + "난투",name + "피의 울음소리",name + "그롬마쉬 헬스크림");
						menuList.setItems(mList);
					}
				});
			 } else if(job == "성기사") {
				 jList.remove("전사");
				 jList.remove("흑마법사");
				 jList.remove("주술사");
				 jList.remove("사냥꾼");
				 jList.remove("마법사");
				 jList.remove("도적");
				 jList.remove("사제");
				 jList.remove("드루이드");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					 if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "고귀한 희생", name + "구원", name + "눈에는 눈", name + "지혜의 축복", name + "참회", name + "여명회 파수병", name + "알도르 평화감시단", name + "정의의 칼날", name + "평등", name + "신의 격노", name + "정의", name + "축복받은 용사", name + "응징의 격노", name + "신의 축복", name + "티리온 폴드링");
						 menuList.setItems(mList);
					 }
				 });
			 } else if(job == "흑마법사") {
				 jList.remove("전사");
				 jList.remove("성기사");
				 jList.remove("주술사");
				 jList.remove("사냥꾼");
				 jList.remove("마법사");
				 jList.remove("도적");
				 jList.remove("사제");
				 jList.remove("드루이드");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					 if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "공허의 부름",name + "피의 임프",name + "화염 임프",name + "악마의 불꽃",name + "공허의 괴물",name + "악마 감지",name + "지옥수호병",name + "소환의 문",name + "암흑불길",name + "지옥의 군주",name + "운명의 파멸",name + "영혼 착취",name + "공성파괴자",name + "뒤틀린 황천",name + "군주 자락서스" );
						 menuList.setItems(mList);
					 }
				 });
			 } else if(job == "주술사") {
				 jList.remove("전사");
				 jList.remove("성기사");
				 jList.remove("흑마법사");
				 jList.remove("사냥꾼");
				 jList.remove("마법사");
				 jList.remove("도적");
				 jList.remove("사제");
				 jList.remove("드루이드");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					 if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "갈래 번개",name + "대지 충격",name + "먼지 악령",name + "번개 화살",name + "고대의 영혼",name + "폭풍으로 벼려낸 도끼",name + "마나 해일 토템",name + "번개 폭풍",name + "속박 풀린 정령",name + "야수 정령",name + "용암 폭발",name + "천리안",name + "대지의 정령",name + "둠해머",name + "바람의 군주 알아키르");
						 menuList.setItems(mList);
					 }
				 });
			 } else if(job == "사냥꾼") {
				 jList.remove("전사");
				 jList.remove("성기사");
				 jList.remove("흑마법사");
			 	 jList.remove("주술사");
			 	 jList.remove("마법사");
			 	 jList.remove("도적");
			 	 jList.remove("사제");
			 	 jList.remove("드루이드");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "야수의 격노",name + "눈속임",name + "뱀 덫",name + "빙결의 덫",name + "섬광",name + "저격",name + "청소부 하이애나",name + "폭발의 덫",name + "개들을 풀어라",name + "독수리뿔 장궁",name + "치명적인 사격",name + "폭발 사격",name + "사바나 사자",name + "검투사의 장궁",name + "왕 크루쉬");
						 menuList.setItems(mList);
					 }
				 });
			 } else if(job == "마법사") {
				 jList.remove("전사");
				 jList.remove("성기사");
				 jList.remove("흑마법사");
			 	 jList.remove("주술사");
			 	 jList.remove("사냥꾼");
			 	 jList.remove("도적");
			 	 jList.remove("사제");
			 	 jList.remove("드루이드");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					 if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "지식의 고서",name + "고드름",name + "마나 지룡",name + "마술사의 수습생",name + "거울상",name + "마법 차단",name + "얼음 보호막",name + "주문왜곡사",name + "증발시키기",name + "키린 토 마법사",name + "냉기 돌풍",name + "에테리얼 비전술사",name + "눈보라",name + "대마법사 안토니다스",name + "불덩이 작렬");
						 menuList.setItems(mList);
					 }
				 });
			 } else if(job == "도적") {
				 jList.remove("전사");
				 jList.remove("성기사");
				 jList.remove("흑마법사");
			 	 jList.remove("주술사");
			 	 jList.remove("사냥꾼");
			 	 jList.remove("마법사");
			 	 jList.remove("사제");
			 	 jList.remove("드루이드");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					 if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "그림자 밟기",name + "마음가짐",name + "슬쩍",name + "냉혈",name + "데피아즈단 두목",name + "배신",name + "절개",name + "침착한 암살자",name + "SI:7 요원",name + "머리 후려치기",name + "에드윈 밴클리프",name + "전멸의 비수",name + "위장의 대가",name + "폭풍의 칼날",name + "납치범");
						 menuList.setItems(mList);
					 }
				 });
			 } else if(job == "사제") {
				 jList.remove("전사");
				 jList.remove("성기사");
				 jList.remove("흑마법사");
			 	 jList.remove("주술사");
			 	 jList.remove("사냥꾼");
			 	 jList.remove("마법사");
			 	 jList.remove("도적");
			 	 jList.remove("드루이드");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					 if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "치유의 마법진",name + "침묵",name + "내면의 열정",name + "빛샘",name + "생각 훔치기",name + "어둠의 형상",name + "대규모 무효화",name + "빛의 정령",name + "심리 조작",name + "아키나이 영혼사제",name + "암흑의 광기",name + "비밀결사단 어둠사제",name + "사원 집행자",name + "신성한 불꽃",name + "예언자 벨렌");
						 menuList.setItems(mList);
					 }
				 });
			 } else if(job == "드루이드") {
				 jList.remove("전사");
				 jList.remove("성기사");
				 jList.remove("흑마법사");
			 	 jList.remove("주술사");
			 	 jList.remove("사냥꾼");
			 	 jList.remove("마법사");
			 	 jList.remove("도적");
			 	 jList.remove("사제");
					cBox.setItems(jList);
				 dlcBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obser, String o, String dlc) -> {
					 if(dlc == "오리지널") {
						 mList = FXCollections.observableArrayList(name + "야생성",name + "야생의 힘",name + "천벌",name + "자연의 징표",name + "물기",name + "숲의 수호자",name + "숲의 영혼",name + "발톱의 드루이드",name + "별똥별",name + "자연의 군대",name + "육성",name + "전쟁의 고대정령",name + "지식의 고대정령",name + "야생의 선물",name + "세나리우스");
						 menuList.setItems(mList);
					 }
				 });
			 }
		 });
		 
		 menuList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observ, String o, String select) -> {
				System.out.println(o + "->" + select);
				this.select = select;
				selectb = true;
				selecb = false;
			 });
		 
		 viewList.setOnMouseClicked((MouseEvent)->{
			 	Object obj = viewList.getSelectionModel().getSelectedItem();	
			 	System.out.println(obj);
			 });
		 
		 neList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observ, String o, String selec) -> {
			 System.out.println(o + "->" + selec);
			 this.selec = selec;
			 selectb = false;
			 selecb = true;
		 });

		 
		 view();
	}
	
	public void record() {
		 if(select == null) {
			 System.out.println("no empty");
			 return;
		 }
		 Connection con = JDBCUtil.getConnection();
		 PreparedStatement pstmt = null;
		 String sql = "INSERT INTO card (info, number) VALUES (?, ?)";
		 
		 try {
			 if(num == 30) {
				 System.out.println("카드는 30장까지만 넣을 수 있습니다!!");
				 return;
			 }
			int cost = 0;
			pstmt = con.prepareStatement(sql);
			if(selectb) {
				if(list.toString().contains(select.toString())) {
					System.out.println("같은 카드를 또 넣을 수 없습니다!!");
					return;
				}
				pstmt.setString(1, select);
			} else if(selecb) {
				if(list.toString().contains(selec.toString())) {
					System.out.println("같은 카드를 또 넣을 수 없습니다!!");
					return;
				}
				pstmt.setString(1, selec);
			}
			if(txtNumber.getText().isEmpty()) {
				System.out.println("no empty");
				return;
			}
			if(Integer.parseInt(txtNumber.getText()) > 2) {
				System.out.println("2장 이상은 가질 수 없습니다!!");
				return;
			} else if(Integer.parseInt(txtNumber.getText()) < 1) {
				System.out.println("최소 1장은 넣어야 합니다!!");
				return;
			}
//			if(selectb) {
//				if(select.contains("그롬마쉬 헬스크림") || select.contains("세나리우스") || select.contains("왕 크루쉬") || select.contains("대마법사 안토니다스") || select.contains("티리온 폴드링") || select.contains("예언자 벨렌") || select.contains("에드윈 벤클리프") || select.contains("바람의 군주 알아키르") || select.contains("군주 자락서스") && Integer.parseInt(txtNumber.getText()) != 1) {
//					System.out.println("전설 카드는 1장만 넣을 수 있습니다!!");
//					return;
//				}
//			} else if(selecb) {
//				if(selec.contains("내트 페이글") || selec.contains("밀하우스 마나스톰") || selec.contains("전승지기 초") || selec.contains("혈법사 탈노스") || selec.contains("밀림의 왕 무클라") || selec.contains("빛나래") || selec.contains("수석땜장이 오버스파크") || selec.contains("리로이 젠킨스") || selec.contains("선장 그린스킨") || selec.contains("해리슨 존스") || selec.contains("괴수") || selec.contains("들창코") || selec.contains("일리단 스톰레이지") || selec.contains("케른 블러드후프") || selec.contains("흑기사") || selec.contains("남작 게돈") || selec.contains("종교재판관 화이트메인") || selec.contains("그룰") || selec.contains("노즈도르무") || selec.contains("말리고스") || selec.contains("일렉스트라자") || selec.contains("오닉시아") || selec.contains("이세라") || selec.contains("데스윙") && Integer.parseInt(txtNumber.getText()) != 1) {
//					System.out.println("전설 카드는 1장만 넣을 수 있습니다!!");
//					return;
//				}
//			}
//			
			pstmt.setInt(2, Integer.parseInt(txtNumber.getText()));
//			if(select.contains("강화!") || select.contains("방패 밀쳐내기")|| select.contains("야생성")|| select.contains("야수의 격노")|| select.contains("지식의 고서")|| select.contains("고귀한 희생")|| select.contains("구원")|| select.contains("눈에는 눈")|| select.contains("지혜의 축복")|| select.contains("참회")|| select.contains("내면의 열정")|| select.contains("슬쩍")|| select.contains("갈래 번개")|| select.contains("대지 충격")|| select.contains("먼지 악령")|| select.contains("번개 화살")|| select.contains("공허의 부름")|| select.contains("피의 임프")|| select.contains("화염 임프")|| select.contains("강화!")|| select.contains("방패 밀쳐내기")|| selec.contains("가혹한 하사관")|| selec.contains("굶주린 게")|| selec.contains("남쪽바다 갑판원")|| selec.contains("늑대인간 침투요원")|| selec.contains("멀록 파도술사")|| selec.contains("방패병")|| selec.contains("붉은해적단 바다사냥꾼")|| selec.contains("비밀지기")|| selec.contains("빛의 감시자")|| selec.contains("어린 용매")|| selec.contains("오염된 노움")|| selec.contains("은빛십자군 종자")|| selec.contains("젊은 여사제")|| selec.contains("화난 닭")) {
//				cost = 1;
//			} else if(select.contains("격돌") || select.contains("광란") || select.contains("방어구 제작자") || select.contains("잔인한 감독관") || select.contains("전투 격노") || select.contains("지휘의 외침")|| select.contains("야생의 힘")|| select.contains("천벌")|| select.contains("눈속임")|| select.contains("뱀 덫")|| select.contains("빙결의 덫")|| select.contains("섬광")|| select.contains("저격")|| select.contains("청소부 하이에나")|| select.contains("폭발의 덫")|| select.contains("고드름")|| select.contains("마나 지룡")|| select.contains("마술사의 수습생")|| select.contains("여명회 파수병")|| select.contains("빛샘")|| select.contains("냉혈")|| select.contains("데피아즈단 두목")|| select.contains("배신")|| select.contains("절개")|| select.contains("침착한 암살자")|| select.contains("고대의 영혼")|| select.contains("폭풍으로 벼려낸 도끼")|| select.contains("악마의 불꽃")|| select.contains("격돌")|| select.contains("광란")|| select.contains("방어구 제작자")|| select.contains("잔인한 감독관")|| select.contains("전투 격노")|| select.contains("지휘의 외침")|| selec.contains("검 제작의 대가")|| selec.contains("고대의 감시자")|| selec.contains("광기의 연금술사")|| selec.contains("광기의 화염술사")|| selec.contains("광포한 늑대 우두머리")|| selec.contains("내트 페이글")|| selec.contains("단검 곡예사")|| selec.contains("마나 망령")|| selec.contains("마나 중독자")|| selec.contains("밀하우스 마나스톰")|| selec.contains("붉은해적단 약탈자")|| selec.contains("성난태양 파수병")|| selec.contains("아마니 광전사")|| selec.contains("요정용")|| selec.contains("자그마한 소환사")|| selec.contains("전리품 수집가")|| selec.contains("전승지기 초")|| selec.contains("젋은 양조사")|| selec.contains("정신 나간 폭격수")|| selec.contains("파멸의 예언자")|| selec.contains("혈법사 탈노스")) {
//				cost = 2;
//			} else if(select.contains("거품 무는 광전사")|| select.contains("자연의 징표")|| select.contains("개들을 풀어라")|| select.contains("독수리뿔 장궁")|| select.contains("치명적인 사격")|| select.contains("거울상")|| select.contains("마법 차단")|| select.contains("얼음 보호막")|| select.contains("주문왜곡사")|| select.contains("증발시키기")|| select.contains("키린 토 마법사")|| select.contains("알도르 평화감시단")|| select.contains("정의의 칼날")|| select.contains("생각 훔치기")|| select.contains("어둠의 형상")|| select.contains("SI:7요원")|| select.contains("머리 후려치기")|| select.contains("에드윈 밴클리프")|| select.contains("전멸의 비수")|| select.contains("마나 해일 토템")|| select.contains("번개 폭풍")|| select.contains("속박 풀린 정령")|| select.contains("야수 정령")|| select.contains("용암 폭발")|| select.contains("천리안")|| select.contains("공허의 괴물")|| select.contains("악마 감지")|| select.contains("지옥수호병")|| selec.contains("고통의 수행사제")|| selec.contains("굶주린 식인 구울")|| selec.contains("남쪽바다 선장")|| selec.contains("노움 자동경보기")|| selec.contains("대지 고리회 선견자")|| selec.contains("멀록 전투대장")|| selec.contains("무쇠부리 올빼미")|| selec.contains("밀림 표범")|| selec.contains("밀림의 왕 무클라")|| selec.contains("부상당한 검귀")|| selec.contains("붉은십자군 성전사")|| selec.contains("비전골렘")|| selec.contains("빛나래")|| selec.contains("수석땜장이 오버스파크")|| selec.contains("스랄마 선견자")|| selec.contains("시린빛 예언자")|| selec.contains("임프 소환사")|| selec.contains("정신 지배 기술자")|| selec.contains("퀘스트 중인 모험가")|| selec.contains("타우렌 전사")|| selec.contains("파괴전차")|| selec.contains("하수아비골렘")|| selec.contains("혈기사")|| selec.contains("황제 코브라")|| selec.contains("흉포한 늑대인간")) {
//				cost = 3;
//			} else if(select.contains("아라시 무기제작자") || select.contains("필사의 일격")|| select.contains("물기")|| select.contains("숲의 수호자")|| select.contains("숲의 영혼")|| select.contains("냉기 돌풍")|| select.contains("에테리얼 비전술사")|| select.contains("평등")|| select.contains("대규모 무효화")|| select.contains("빛의 정령")|| select.contains("심리 조작")|| select.contains("아키나이 영혼사제")|| select.contains("암흑의 광기")|| select.contains("위장의 대가")|| select.contains("폭풍의 칼날")|| select.contains("소환의 문")|| select.contains("암흑불길")|| select.contains("지옥의 군주")|| select.contains("아라시 무기제작자")|| select.contains("필사의 일격")|| selec.contains("SI:7침투요원")|| selec.contains("검은무쇠 드워프")|| selec.contains("고대의 마법사")|| selec.contains("고대의 양조사")|| selec.contains("공포의 해적")|| selec.contains("모구샨 감시자")|| selec.contains("보랏빛 여교사")|| selec.contains("실버문 수호병")|| selec.contains("아르거스의 수호자")|| selec.contains("이교도 지도자")|| selec.contains("주문파괴자")|| selec.contains("황혼의 비룡")) {
//				cost = 4;
//			} else if(select.contains("난투")|| select.contains("발톱의 드루이드")|| select.contains("별똥별")|| select.contains("자연의 군대")|| select.contains("폭발 사격")|| select.contains("신의 격노")|| select.contains("정의")|| select.contains("축복받은 용사")|| select.contains("대지의 정령")|| select.contains("둠해머")|| select.contains("운명의 파멸")|| select.contains("난투")|| selec.contains("가시덤불 호랑이")|| selec.contains("나 이런 사냥꾼이야")|| selec.contains("날뛰는 코도")|| selec.contains("누더기골렘")|| selec.contains("리로이 젠킨스")|| selec.contains("선장 그린스킨")|| selec.contains("수렁이끼괴물")|| selec.contains("얼굴 없는 배후자")|| selec.contains("원한 맺힌 대장장이")|| selec.contains("은빛 성기사단 기사")|| selec.contains("투자개발회사 용병")|| selec.contains("해리슨 존스")) {
//				cost = 5;
//			} else if(select.contains("육성")|| select.contains("사바나 사자")|| select.contains("눈보라")|| select.contains("응징의 격노")|| select.contains("비밀결사단 어둠사제")|| select.contains("사원 집행자")|| select.contains("신성한 불꽃")|| select.contains("납치범")|| select.contains("영혼 착취")|| selec.contains("가젯잔 경매인")|| selec.contains("괴수")|| selec.contains("냉기 정령")|| selec.contains("들창코")|| selec.contains("성난바람 하피")|| selec.contains("엘룬의 여사제")|| selec.contains("은빛십자군 부대장")|| selec.contains("일리단 스톰레이지")|| selec.contains("케른 블러드후프")|| selec.contains("태양길잡이")|| selec.contains("흑기사")) {
//				cost = 6;
//			} else if(select.contains("피의 울음소리")|| select.contains("전쟁의 고대정령")|| select.contains("지식의 고대정령")|| select.contains("검투사 장굴")|| select.contains("대마법사 안토니다스")|| select.contains("예언자 벨렌")|| select.contains("공성파괴자")|| select.contains("피의 울음소리")|| selec.contains("남작 게돈")|| selec.contains("라벤홀트 암살자")|| selec.contains("불모의 땅 마구간지기")|| selec.contains("종교재판관 화이트메인")) {
//				cost = 7;
//			} else if(select.contains("그롬마쉬 헬스크림") || select.contains("티리온 폴드링")|| select.contains("바람의 군주 알아키르")|| select.contains("뒤틀린 황천") || select.contains("야생의 선물")|| select.contains("신의 축복")|| selec.contains("그룰")|| selec.contains("비전 포식자")) {
//				cost = 8;
//			} else if(select.contains("세나리우스")|| select.contains("왕 크루쉬")|| select.contains("군주 자락서스")|| selec.contains("노즈도르무")|| selec.contains("말리고스")|| selec.contains("알렉스트라자")|| selec.contains("오닉시아")|| selec.contains("이세라")) {
//				cost = 9;
//			} else if(select.contains("불덩이 작렬")|| selec.contains("데스윙")|| selec.contains("바다 거인")) {
//				cost = 10;
//			} else if(selec.contains("산악 거인")) {
//				cost = 12;
//			}
			num += Integer.parseInt(txtNumber.getText());
			System.out.println(num);
			pstmt.executeUpdate();
			System.out.println("DB 삽입 성공");
			view();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void view() {
		list.clear();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM card";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(makeCardVO(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 불러오기 실패");
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	private subVO makeCardVO(ResultSet rs) throws Exception{
		subVO temp = new subVO();
		temp.setId(rs.getInt("id"));
		temp.setInfo(rs.getString("info"));
		temp.setNumber(rs.getInt("number"));
		
		return temp;
	}
	
	public void updateCard() {
		String name = viewList.getSelectionModel().getSelectedItem().getInfo();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt= null;
		String sql = "update card set number = ? where info = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			if(txtNumber.getText().isEmpty()) {
				System.out.println("no empty");
				return;
			}
			if(Integer.parseInt(txtNumber.getText()) > 2) {
				System.out.println("2장 이상은 가질 수 없습니다!!");
				return;
			} else if(Integer.parseInt(txtNumber.getText()) < 1) {
				System.out.println("최소 1장은 넣어야 합니다!!");
				return;
			}
			pstmt.setInt(1, Integer.parseInt(txtNumber.getText()));
			pstmt.setString(2, name);
			System.out.println(name);
			
			pstmt.executeUpdate();
			System.out.println("DB 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 오류2");
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		view();
	}
	
	public void deleteCard() {
		String name = viewList.getSelectionModel().getSelectedItem().getInfo();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from card where info = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			System.out.println("DB 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB오류3");
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		
		view();
	}
	
	public void setName() {
		if(txtName.getText().isEmpty()) {
			System.out.println("no empty");
			return;
		}
		label.setText(txtName.getText());
	}
}
