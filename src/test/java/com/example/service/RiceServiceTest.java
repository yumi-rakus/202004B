package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.domain.Rice;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RiceServiceTest {

	@Autowired
	private RiceService riceService;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void findAllTest1() {

		List<Rice> riceList = riceService.findAll();

		assertEquals(16, riceList.size(), "件数が期待される結果と異なります。");

		assertEquals(1, riceList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産こしひかり", riceList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("新潟県産コシヒカリは、日本人が伝統的に有している粘りと弾性の嗜好にぴったりと適合する特性を持っています。また、香り・甘み・つや・白さなどおいしいご飯の条件をすべて満たしています。",
				riceList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_kosi5.png", riceList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(0).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(2, riceList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産魚沼コシヒカリ", riceList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"日本一と言われる魚沼地区は、山間地独特の気候のため昼夜の寒暖の差が大きく、またお米を育てる豊富な湧き水に恵まれています。このような条件の地域で育った魚沼産コシヒカリは、味、つや、粘りともお米の最高傑作です。",
				riceList.get(1).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_uonuma_koshi.png", riceList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(1).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(3, riceList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産新之助", riceList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"「コシヒカリとはベクトルが異なるおいしさを持つ米を提供したい」との思いで誕生した2017年販売開始の新品種。大粒できれいなツヤがあり、ほんのりとした香り、豊潤な甘みとコク、しっかりした粘りと弾力を併せ持ちます。 「硬さ」と「粘り」という相反する要素を兼ね備えており、冷めても硬くなりにくく、おいしさを保つ新潟自慢のお米です。",
				riceList.get(2).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_shinnosuke.png", riceList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(2).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, riceList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("北海道ゆめぴりか", riceList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"遂にたどり着いた、ほど良い粘りと甘み。そして、炊きあがりの美しさ。その優れた品質から、「日本一おいしい米を」という北海道民の「夢」に、アイヌ語で美しいを意味する「ピリカ」を合わせて名付けられました。",
				riceList.get(3).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("hkd_01.png", riceList.get(3).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(3).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(5, riceList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("北海道ななつぼし", riceList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals("「きらら397」や「ひとめぼれ」の血を引いており、良いお米の条件とされる「味」「白さ」「つや」「粘り」「香り」「柔らかさ」「口当たり」７つすべてに胸を張る自信作です。",
				riceList.get(4).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("hkd_03.png", riceList.get(4).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(4).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, riceList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("山形県産つや姫", riceList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals("つや姫は、ブナの原生林が育む肥沃な土壌、四季豊かな山形の風土が生み育てた、おいしいお米のルーツ「亀ノ尾」の正当な系譜から誕生した山形のお米です。際立つ炊きあがりの白さ・つや・甘さが自慢です。",
				riceList.get(5).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ymg_01.png", riceList.get(5).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(5).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(7, riceList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("秋田あきたこまち", riceList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals("「あきたこまち」は、自然豊かな秋田で丹精込めて作られ、食味の評判は最高。光沢が冴え、あっさりとした食感で冷めても味が落ちない特性を備えています。",
				riceList.get(6).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("akt_komachi.png", riceList.get(6).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(6).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(8, riceList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("青森青天の霹靂", riceList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals("青森県が、誰もが驚くような旨さを目指し、約10年をかけて開発した新品種。粒がやや大きめで食べごたえがあり、上品な甘みの残る味わいが特徴です。",
				riceList.get(7).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("aom_seiten.png", riceList.get(7).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(7).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(9, riceList.get(8).getId(), "IDが期待される結果と異なります。");
		assertEquals("岩手県産ひとめぼれ", riceList.get(8).getName(), "名前が期待される結果と異なります。");
		assertEquals("大粒でハリがあり、飽きの来ない甘みがあります。母親であるコシヒカリの特徴を受け継ぎ、粘りがあって炊き上がりはツヤツヤと色よく、うまみ、甘み、香りも申し分ありません。",
				riceList.get(8).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("iwt_hitome_s.png", riceList.get(8).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(8).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, riceList.get(9).getId(), "IDが期待される結果と異なります。");
		assertEquals("福井県産ミルキークイーン", riceList.get(9).getName(), "名前が期待される結果と異なります。");
		assertEquals("玄米が半透明なのでお米の表面が乳白色に見えること良質米の女王という意味を込めて名付けられました。粘りが強いという特性があり、食べるとモチモチした食感が味わえます。",
				riceList.get(9).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("milky_q.png", riceList.get(9).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(9).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, riceList.get(10).getId(), "IDが期待される結果と異なります。");
		assertEquals("愛知あいちのかおり", riceList.get(10).getName(), "名前が期待される結果と異なります。");
		assertEquals("「あいちのかおり」は、大粒でしっかりとした食感と、炊飯した際のごはんの光沢が特徴です", riceList.get(10).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals("aic_04.png", riceList.get(10).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(10).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(12, riceList.get(11).getId(), "IDが期待される結果と異なります。");
		assertEquals("熊本森のくまさん", riceList.get(11).getName(), "名前が期待される結果と異なります。");
		assertEquals("夏目漱石が熊本在住の際、豊かな緑に感嘆し、「森の都熊本」と表現しました。その「森の都」「熊本」で生産されたお米ということから、「森のくまさん」と名付けられました。",
				riceList.get(11).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("kmt_01.png", riceList.get(11).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(11).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(13, riceList.get(12).getId(), "IDが期待される結果と異なります。");
		assertEquals("宮崎コシヒカリ", riceList.get(12).getName(), "名前が期待される結果と異なります。");
		assertEquals("温暖な気候の宮崎は、早い時期から新米が穫れることで知られています。", riceList.get(12).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("myz_01.png", riceList.get(12).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(12).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(14, riceList.get(13).getId(), "IDが期待される結果と異なります。");
		assertEquals("もち麦ごはん", riceList.get(13).getName(), "名前が期待される結果と異なります。");
		assertEquals("食物繊維が玄米の約4倍含まれています。水溶性と不溶性の2つの食物繊維がバランスよく含まれているのも特徴です。もち麦は、もち種の大麦なのでぷちぷちした食感があり、冷めてもおいしさが長続きします。",
				riceList.get(13).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("mochi.png", riceList.get(13).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(13).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(15, riceList.get(14).getId(), "IDが期待される結果と異なります。");
		assertEquals("玄米", riceList.get(14).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"玄米は白米と比べると、食物繊維が4～6倍近くあり、便秘にはもちろん、 体の中の余分なコレステロールや糖分、発がん物質などの有害物質を排出して、体を内側からすっきりさせてくれます。さらに、ミネラル類も充実していて、体のさまざまなバランスも整えてくれます。",
				riceList.get(14).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("genmai_03.png", riceList.get(14).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(14).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(16, riceList.get(15).getId(), "IDが期待される結果と異なります。");
		assertEquals("雑穀米", riceList.get(15).getName(), "名前が期待される結果と異なります。");
		assertEquals("雑穀米は玄米、あわ、キビ、もち麦などを白米に混ぜ込んだもので、食物繊維やミネラル、ビタミン類も含まれているため、栄養に優れています。",
				riceList.get(15).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("zakkoku.png", riceList.get(15).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(15).getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest1() {
		Rice rice = riceService.findById(1);

		assertEquals(1, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産こしひかり", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("新潟県産コシヒカリは、日本人が伝統的に有している粘りと弾性の嗜好にぴったりと適合する特性を持っています。また、香り・甘み・つや・白さなどおいしいご飯の条件をすべて満たしています。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_kosi5.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest2() {
		Rice rice = riceService.findById(2);

		assertEquals(2, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産魚沼コシヒカリ", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"日本一と言われる魚沼地区は、山間地独特の気候のため昼夜の寒暖の差が大きく、またお米を育てる豊富な湧き水に恵まれています。このような条件の地域で育った魚沼産コシヒカリは、味、つや、粘りともお米の最高傑作です。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_uonuma_koshi.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest3() {
		Rice rice = riceService.findById(3);

		assertEquals(3, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産新之助", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"「コシヒカリとはベクトルが異なるおいしさを持つ米を提供したい」との思いで誕生した2017年販売開始の新品種。大粒できれいなツヤがあり、ほんのりとした香り、豊潤な甘みとコク、しっかりした粘りと弾力を併せ持ちます。 「硬さ」と「粘り」という相反する要素を兼ね備えており、冷めても硬くなりにくく、おいしさを保つ新潟自慢のお米です。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_shinnosuke.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest4() {
		Rice rice = riceService.findById(4);

		assertEquals(4, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("北海道ゆめぴりか", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"遂にたどり着いた、ほど良い粘りと甘み。そして、炊きあがりの美しさ。その優れた品質から、「日本一おいしい米を」という北海道民の「夢」に、アイヌ語で美しいを意味する「ピリカ」を合わせて名付けられました。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("hkd_01.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest5() {
		Rice rice = riceService.findById(5);

		assertEquals(5, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("北海道ななつぼし", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("「きらら397」や「ひとめぼれ」の血を引いており、良いお米の条件とされる「味」「白さ」「つや」「粘り」「香り」「柔らかさ」「口当たり」７つすべてに胸を張る自信作です。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("hkd_03.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest6() {
		Rice rice = riceService.findById(6);

		assertEquals(6, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("山形県産つや姫", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("つや姫は、ブナの原生林が育む肥沃な土壌、四季豊かな山形の風土が生み育てた、おいしいお米のルーツ「亀ノ尾」の正当な系譜から誕生した山形のお米です。際立つ炊きあがりの白さ・つや・甘さが自慢です。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ymg_01.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest7() {
		Rice rice = riceService.findById(7);

		assertEquals(7, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("秋田あきたこまち", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("「あきたこまち」は、自然豊かな秋田で丹精込めて作られ、食味の評判は最高。光沢が冴え、あっさりとした食感で冷めても味が落ちない特性を備えています。", rice.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals("akt_komachi.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest8() {
		Rice rice = riceService.findById(8);

		assertEquals(8, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("青森青天の霹靂", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("青森県が、誰もが驚くような旨さを目指し、約10年をかけて開発した新品種。粒がやや大きめで食べごたえがあり、上品な甘みの残る味わいが特徴です。", rice.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals("aom_seiten.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest9() {
		Rice rice = riceService.findById(9);

		assertEquals(9, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("岩手県産ひとめぼれ", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("大粒でハリがあり、飽きの来ない甘みがあります。母親であるコシヒカリの特徴を受け継ぎ、粘りがあって炊き上がりはツヤツヤと色よく、うまみ、甘み、香りも申し分ありません。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("iwt_hitome_s.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest10() {
		Rice rice = riceService.findById(10);

		assertEquals(10, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("福井県産ミルキークイーン", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("玄米が半透明なのでお米の表面が乳白色に見えること良質米の女王という意味を込めて名付けられました。粘りが強いという特性があり、食べるとモチモチした食感が味わえます。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("milky_q.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest11() {
		Rice rice = riceService.findById(11);

		assertEquals(11, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("愛知あいちのかおり", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("「あいちのかおり」は、大粒でしっかりとした食感と、炊飯した際のごはんの光沢が特徴です", rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("aic_04.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest12() {
		Rice rice = riceService.findById(12);

		assertEquals(12, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("熊本森のくまさん", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("夏目漱石が熊本在住の際、豊かな緑に感嘆し、「森の都熊本」と表現しました。その「森の都」「熊本」で生産されたお米ということから、「森のくまさん」と名付けられました。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("kmt_01.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest13() {
		Rice rice = riceService.findById(13);

		assertEquals(13, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("宮崎コシヒカリ", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("温暖な気候の宮崎は、早い時期から新米が穫れることで知られています。", rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("myz_01.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest14() {
		Rice rice = riceService.findById(14);

		assertEquals(14, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("もち麦ごはん", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("食物繊維が玄米の約4倍含まれています。水溶性と不溶性の2つの食物繊維がバランスよく含まれているのも特徴です。もち麦は、もち種の大麦なのでぷちぷちした食感があり、冷めてもおいしさが長続きします。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("mochi.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest15() {
		Rice rice = riceService.findById(15);

		assertEquals(15, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("玄米", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"玄米は白米と比べると、食物繊維が4～6倍近くあり、便秘にはもちろん、 体の中の余分なコレステロールや糖分、発がん物質などの有害物質を排出して、体を内側からすっきりさせてくれます。さらに、ミネラル類も充実していて、体のさまざまなバランスも整えてくれます。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("genmai_03.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByIdTest16() {
		Rice rice = riceService.findById(16);

		assertEquals(16, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("雑穀米", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("雑穀米は玄米、あわ、キビ、もち麦などを白米に混ぜ込んだもので、食物繊維やミネラル、ビタミン類も含まれているため、栄養に優れています。", rice.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals("zakkoku.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(rice.getDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findAllNonDeletedAndUpdateDeleteFlagTest1() {

		riceService.updateDeleteFlag(true, 1);
		Rice rice = riceService.findById(1);

		assertEquals(1, rice.getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産こしひかり", rice.getName(), "名前が期待される結果と異なります。");
		assertEquals("新潟県産コシヒカリは、日本人が伝統的に有している粘りと弾性の嗜好にぴったりと適合する特性を持っています。また、香り・甘み・つや・白さなどおいしいご飯の条件をすべて満たしています。",
				rice.getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_kosi5.png", rice.getImagePath(), "画像パスが期待される結果と異なります。");
		assertTrue(rice.getDeleted(), "削除フラグが期待される結果と異なります。");

		List<Rice> riceList = riceService.findAllNonDeleted();
		assertEquals(15, riceList.size(), "件数が期待される結果と異なります。");

		assertEquals(2, riceList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産魚沼コシヒカリ", riceList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"日本一と言われる魚沼地区は、山間地独特の気候のため昼夜の寒暖の差が大きく、またお米を育てる豊富な湧き水に恵まれています。このような条件の地域で育った魚沼産コシヒカリは、味、つや、粘りともお米の最高傑作です。",
				riceList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_uonuma_koshi.png", riceList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(0).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(3, riceList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産新之助", riceList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"「コシヒカリとはベクトルが異なるおいしさを持つ米を提供したい」との思いで誕生した2017年販売開始の新品種。大粒できれいなツヤがあり、ほんのりとした香り、豊潤な甘みとコク、しっかりした粘りと弾力を併せ持ちます。 「硬さ」と「粘り」という相反する要素を兼ね備えており、冷めても硬くなりにくく、おいしさを保つ新潟自慢のお米です。",
				riceList.get(1).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_shinnosuke.png", riceList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(1).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, riceList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("北海道ゆめぴりか", riceList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"遂にたどり着いた、ほど良い粘りと甘み。そして、炊きあがりの美しさ。その優れた品質から、「日本一おいしい米を」という北海道民の「夢」に、アイヌ語で美しいを意味する「ピリカ」を合わせて名付けられました。",
				riceList.get(2).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("hkd_01.png", riceList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(2).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(5, riceList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("北海道ななつぼし", riceList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals("「きらら397」や「ひとめぼれ」の血を引いており、良いお米の条件とされる「味」「白さ」「つや」「粘り」「香り」「柔らかさ」「口当たり」７つすべてに胸を張る自信作です。",
				riceList.get(3).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("hkd_03.png", riceList.get(3).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(3).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, riceList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("山形県産つや姫", riceList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals("つや姫は、ブナの原生林が育む肥沃な土壌、四季豊かな山形の風土が生み育てた、おいしいお米のルーツ「亀ノ尾」の正当な系譜から誕生した山形のお米です。際立つ炊きあがりの白さ・つや・甘さが自慢です。",
				riceList.get(4).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ymg_01.png", riceList.get(4).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(4).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(7, riceList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("秋田あきたこまち", riceList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals("「あきたこまち」は、自然豊かな秋田で丹精込めて作られ、食味の評判は最高。光沢が冴え、あっさりとした食感で冷めても味が落ちない特性を備えています。",
				riceList.get(5).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("akt_komachi.png", riceList.get(5).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(5).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(8, riceList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("青森青天の霹靂", riceList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals("青森県が、誰もが驚くような旨さを目指し、約10年をかけて開発した新品種。粒がやや大きめで食べごたえがあり、上品な甘みの残る味わいが特徴です。",
				riceList.get(6).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("aom_seiten.png", riceList.get(6).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(6).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(9, riceList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("岩手県産ひとめぼれ", riceList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals("大粒でハリがあり、飽きの来ない甘みがあります。母親であるコシヒカリの特徴を受け継ぎ、粘りがあって炊き上がりはツヤツヤと色よく、うまみ、甘み、香りも申し分ありません。",
				riceList.get(7).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("iwt_hitome_s.png", riceList.get(7).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(7).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, riceList.get(8).getId(), "IDが期待される結果と異なります。");
		assertEquals("福井県産ミルキークイーン", riceList.get(8).getName(), "名前が期待される結果と異なります。");
		assertEquals("玄米が半透明なのでお米の表面が乳白色に見えること良質米の女王という意味を込めて名付けられました。粘りが強いという特性があり、食べるとモチモチした食感が味わえます。",
				riceList.get(8).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("milky_q.png", riceList.get(8).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(8).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, riceList.get(9).getId(), "IDが期待される結果と異なります。");
		assertEquals("愛知あいちのかおり", riceList.get(9).getName(), "名前が期待される結果と異なります。");
		assertEquals("「あいちのかおり」は、大粒でしっかりとした食感と、炊飯した際のごはんの光沢が特徴です", riceList.get(9).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals("aic_04.png", riceList.get(9).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(9).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(12, riceList.get(10).getId(), "IDが期待される結果と異なります。");
		assertEquals("熊本森のくまさん", riceList.get(10).getName(), "名前が期待される結果と異なります。");
		assertEquals("夏目漱石が熊本在住の際、豊かな緑に感嘆し、「森の都熊本」と表現しました。その「森の都」「熊本」で生産されたお米ということから、「森のくまさん」と名付けられました。",
				riceList.get(10).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("kmt_01.png", riceList.get(10).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(10).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(13, riceList.get(11).getId(), "IDが期待される結果と異なります。");
		assertEquals("宮崎コシヒカリ", riceList.get(11).getName(), "名前が期待される結果と異なります。");
		assertEquals("温暖な気候の宮崎は、早い時期から新米が穫れることで知られています。", riceList.get(11).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("myz_01.png", riceList.get(11).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(11).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(14, riceList.get(12).getId(), "IDが期待される結果と異なります。");
		assertEquals("もち麦ごはん", riceList.get(12).getName(), "名前が期待される結果と異なります。");
		assertEquals("食物繊維が玄米の約4倍含まれています。水溶性と不溶性の2つの食物繊維がバランスよく含まれているのも特徴です。もち麦は、もち種の大麦なのでぷちぷちした食感があり、冷めてもおいしさが長続きします。",
				riceList.get(12).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("mochi.png", riceList.get(12).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(12).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(15, riceList.get(13).getId(), "IDが期待される結果と異なります。");
		assertEquals("玄米", riceList.get(13).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"玄米は白米と比べると、食物繊維が4～6倍近くあり、便秘にはもちろん、 体の中の余分なコレステロールや糖分、発がん物質などの有害物質を排出して、体を内側からすっきりさせてくれます。さらに、ミネラル類も充実していて、体のさまざまなバランスも整えてくれます。",
				riceList.get(13).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("genmai_03.png", riceList.get(13).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(13).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(16, riceList.get(14).getId(), "IDが期待される結果と異なります。");
		assertEquals("雑穀米", riceList.get(14).getName(), "名前が期待される結果と異なります。");
		assertEquals("雑穀米は玄米、あわ、キビ、もち麦などを白米に混ぜ込んだもので、食物繊維やミネラル、ビタミン類も含まれているため、栄養に優れています。",
				riceList.get(14).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("zakkoku.png", riceList.get(14).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(14).getDeleted(), "削除フラグが期待される結果と異なります。");

	}

	@Test
	public void findAllNonDeletedAndUpdateDeleteFlagTest2() {

		riceService.updateDeleteFlag(true, 1);
		riceService.updateDeleteFlag(true, 3);
		riceService.updateDeleteFlag(true, 5);
		riceService.updateDeleteFlag(true, 7);
		riceService.updateDeleteFlag(true, 9);
		riceService.updateDeleteFlag(true, 11);
		riceService.updateDeleteFlag(true, 13);
		riceService.updateDeleteFlag(true, 15);

		List<Rice> riceList = riceService.findAllNonDeleted();

		assertEquals(8, riceList.size(), "件数が期待される結果と異なります。");

		assertEquals(2, riceList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("新潟県産魚沼コシヒカリ", riceList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"日本一と言われる魚沼地区は、山間地独特の気候のため昼夜の寒暖の差が大きく、またお米を育てる豊富な湧き水に恵まれています。このような条件の地域で育った魚沼産コシヒカリは、味、つや、粘りともお米の最高傑作です。",
				riceList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ngt_uonuma_koshi.png", riceList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(0).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, riceList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("北海道ゆめぴりか", riceList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals(
				"遂にたどり着いた、ほど良い粘りと甘み。そして、炊きあがりの美しさ。その優れた品質から、「日本一おいしい米を」という北海道民の「夢」に、アイヌ語で美しいを意味する「ピリカ」を合わせて名付けられました。",
				riceList.get(1).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("hkd_01.png", riceList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(1).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, riceList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("山形県産つや姫", riceList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("つや姫は、ブナの原生林が育む肥沃な土壌、四季豊かな山形の風土が生み育てた、おいしいお米のルーツ「亀ノ尾」の正当な系譜から誕生した山形のお米です。際立つ炊きあがりの白さ・つや・甘さが自慢です。",
				riceList.get(2).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("ymg_01.png", riceList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(2).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(8, riceList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("青森青天の霹靂", riceList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals("青森県が、誰もが驚くような旨さを目指し、約10年をかけて開発した新品種。粒がやや大きめで食べごたえがあり、上品な甘みの残る味わいが特徴です。",
				riceList.get(3).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("aom_seiten.png", riceList.get(3).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(3).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, riceList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("福井県産ミルキークイーン", riceList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals("玄米が半透明なのでお米の表面が乳白色に見えること良質米の女王という意味を込めて名付けられました。粘りが強いという特性があり、食べるとモチモチした食感が味わえます。",
				riceList.get(4).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("milky_q.png", riceList.get(4).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(4).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(12, riceList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("熊本森のくまさん", riceList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals("夏目漱石が熊本在住の際、豊かな緑に感嘆し、「森の都熊本」と表現しました。その「森の都」「熊本」で生産されたお米ということから、「森のくまさん」と名付けられました。",
				riceList.get(5).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("kmt_01.png", riceList.get(5).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(5).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(14, riceList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("もち麦ごはん", riceList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals("食物繊維が玄米の約4倍含まれています。水溶性と不溶性の2つの食物繊維がバランスよく含まれているのも特徴です。もち麦は、もち種の大麦なのでぷちぷちした食感があり、冷めてもおいしさが長続きします。",
				riceList.get(6).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("mochi.png", riceList.get(6).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(6).getDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(16, riceList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("雑穀米", riceList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals("雑穀米は玄米、あわ、キビ、もち麦などを白米に混ぜ込んだもので、食物繊維やミネラル、ビタミン類も含まれているため、栄養に優れています。",
				riceList.get(7).getDescription(), "説明が期待される結果と異なります。");
		assertEquals("zakkoku.png", riceList.get(7).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(riceList.get(7).getDeleted(), "削除フラグが期待される結果と異なります。");

	}

	@Test
	public void findAllNonDeletedAndUpdateDeleteFlagTest3() {

		riceService.updateDeleteFlag(true, 1);
		riceService.updateDeleteFlag(true, 2);
		riceService.updateDeleteFlag(true, 3);
		riceService.updateDeleteFlag(true, 4);
		riceService.updateDeleteFlag(true, 5);
		riceService.updateDeleteFlag(true, 6);
		riceService.updateDeleteFlag(true, 7);
		riceService.updateDeleteFlag(true, 8);
		riceService.updateDeleteFlag(true, 9);
		riceService.updateDeleteFlag(true, 10);
		riceService.updateDeleteFlag(true, 11);
		riceService.updateDeleteFlag(true, 12);
		riceService.updateDeleteFlag(true, 13);
		riceService.updateDeleteFlag(true, 14);
		riceService.updateDeleteFlag(true, 15);
		riceService.updateDeleteFlag(true, 16);

		List<Rice> riceList = riceService.findAllNonDeleted();

		assertEquals(0, riceList.size(), "件数が期待される結果と異なります。");

	}

	@AfterEach
	public void after() {

		String sql = "UPDATE rices SET deleted = false";

		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql, param);
	}

}
