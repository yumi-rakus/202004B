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

import com.example.domain.Item;

/**
 * ItemService.javaのテスト.
 * 
 * @author yumi takahashi
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemServiceTest {

	@Autowired
	private ItemService itemService;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void findAllTest() {

		List<Item> itemList = itemService.findAll();

		assertEquals(18, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(1, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", itemList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(2, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("ポークポークカレー・ミート", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("2.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(3, itemList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("牛すじカレー", itemList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("トロトロの牛すじとネギの風味が格別な味わいシンプルなカレーです！", itemList.get(2).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("3.jpg", itemList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(2).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, itemList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("味噌カツカレー", itemList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals("マイルドな味付けのカレーに大きくカットした味噌カツをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です", itemList.get(3).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(3).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(3).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("4.jpg", itemList.get(3).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(3).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(5, itemList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("とんかつカレーラーメン", itemList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーはライスだけではありません。ラクラクピザが開発したカレーラーメンは絶品の美味しさ！", itemList.get(4).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(4).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(4).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("5.jpg", itemList.get(4).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(4).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, itemList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", itemList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", itemList.get(5).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(5).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(5).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", itemList.get(5).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(5).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(7, itemList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("濃厚Gorgeous4", itemList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals("こだわりのソースで、旨みとコクを堪能！濃厚Gorgeous4とは、動物由来の原材料を使用していないカレーです。ベジタリアンの方にオススメです",
				itemList.get(6).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2570, itemList.get(6).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3780, itemList.get(6).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("7.jpg", itemList.get(6).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(6).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(8, itemList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("カレーうどん", itemList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクカレー自慢のカレーに魚介のダシ、ローストオニオンのコクが加わった絶妙なスープをお楽しみください", itemList.get(7).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(7).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(7).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("8.jpg", itemList.get(7).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(7).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(9, itemList.get(8).getId(), "IDが期待される結果と異なります。");
		assertEquals("Charity4", itemList.get(8).getName(), "名前が期待される結果と異なります。");
		assertEquals("にんにくとトマトの旨みが効いたスパイスカレー。食べると思わず元気が出るラクラクカレーの自信作", itemList.get(8).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(8).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(8).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("9.jpg", itemList.get(8).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(8).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, itemList.get(9).getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", itemList.get(9).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", itemList.get(9).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(9).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(9).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", itemList.get(9).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(9).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, itemList.get(10).getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", itemList.get(10).getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", itemList.get(10).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(10).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(10).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", itemList.get(10).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(10).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(12, itemList.get(11).getId(), "IDが期待される結果と異なります。");
		assertEquals("季節の野菜カレー", itemList.get(11).getName(), "名前が期待される結果と異なります。");
		assertEquals("季節の野菜が一つになった4種の栄養満点カレー。ラクラクカレーオリジナル商品でベジタリアンの方にオススメです", itemList.get(11).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(11).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(11).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("12.jpg", itemList.get(11).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(11).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(13, itemList.get(12).getId(), "IDが期待される結果と異なります。");
		assertEquals("バラエティー４", itemList.get(12).getName(), "名前が期待される結果と異なります。");
		assertEquals("あらびきスライス牛肉とイタリアンチーズを、トマトソースと特製マヨソースの2種類のソースで召し上がって頂く商品です", itemList.get(12).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(12).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(12).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("13.jpg", itemList.get(12).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(12).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(14, itemList.get(13).getId(), "IDが期待される結果と異なります。");
		assertEquals("えびナスカレー", itemList.get(13).getName(), "名前が期待される結果と異なります。");
		assertEquals("デミグラスソースでじっくり煮込んだ旨味たっぷりのえびとナスのカレー", itemList.get(13).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2980, itemList.get(13).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4460, itemList.get(13).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("14.jpg", itemList.get(13).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(13).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(15, itemList.get(14).getId(), "IDが期待される結果と異なります。");
		assertEquals("Family４", itemList.get(14).getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクピザ自慢「特うまプルコギ」定番「デラックス」お子様に人気「ツナマイルド」女性に好評「チーズ＆チーズ」の４種のおいしさを贅沢に組み合わせました",
				itemList.get(14).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(14).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(14).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("15.jpg", itemList.get(14).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(14).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(16, itemList.get(15).getId(), "IDが期待される結果と異なります。");
		assertEquals("シンプルイズベスト", itemList.get(15).getName(), "名前が期待される結果と異なります。");
		assertEquals("人気ナンバー１！魚介の旨みたっぷり！人気の海の幸と、野菜のリッチなおいしさ", itemList.get(15).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(15).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(15).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("16.jpg", itemList.get(15).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(15).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(17, itemList.get(16).getId(), "IDが期待される結果と異なります。");
		assertEquals("学芸会カレー", itemList.get(16).getName(), "名前が期待される結果と異なります。");
		assertEquals("みんな大好き！学芸会で作るような味を再現！定番のおいしさを味わえます", itemList.get(16).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(16).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(16).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("17.jpg", itemList.get(16).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(16).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(18, itemList.get(17).getId(), "IDが期待される結果と異なります。");
		assertEquals("黄金に輝くチキンカレー", itemList.get(17).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーが黄金に輝く、超高級鶏肉を使用したカレーです", itemList.get(17).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(17).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(17).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("18.jpg", itemList.get(17).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(17).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest1() {

		Item item = itemService.showDetail(1);

		assertEquals(1, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest2() {

		Item item = itemService.showDetail(2);

		assertEquals(2, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("ポークポークカレー・ミート", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です", item.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1490, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("2.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest3() {

		Item item = itemService.showDetail(3);

		assertEquals(3, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("牛すじカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("トロトロの牛すじとネギの風味が格別な味わいシンプルなカレーです！", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("3.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest4() {

		Item item = itemService.showDetail(4);

		assertEquals(4, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("味噌カツカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("マイルドな味付けのカレーに大きくカットした味噌カツをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です", item.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("4.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest5() {

		Item item = itemService.showDetail(5);

		assertEquals(5, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("とんかつカレーラーメン", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーはライスだけではありません。ラクラクピザが開発したカレーラーメンは絶品の美味しさ！", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1900, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("5.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest6() {

		Item item = itemService.showDetail(6);

		assertEquals(6, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", item.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest7() {

		Item item = itemService.showDetail(7);

		assertEquals(7, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("濃厚Gorgeous4", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("こだわりのソースで、旨みとコクを堪能！濃厚Gorgeous4とは、動物由来の原材料を使用していないカレーです。ベジタリアンの方にオススメです", item.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2570, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3780, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("7.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest8() {

		Item item = itemService.showDetail(8);

		assertEquals(8, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("カレーうどん", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクカレー自慢のカレーに魚介のダシ、ローストオニオンのコクが加わった絶妙なスープをお楽しみください", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2160, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("8.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest9() {

		Item item = itemService.showDetail(9);

		assertEquals(9, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("Charity4", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("にんにくとトマトの旨みが効いたスパイスカレー。食べると思わず元気が出るラクラクカレーの自信作", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("9.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest10() {

		Item item = itemService.showDetail(10);

		assertEquals(10, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2160, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest11() {

		Item item = itemService.showDetail(11);

		assertEquals(11, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest12() {

		Item item = itemService.showDetail(12);

		assertEquals(12, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("季節の野菜カレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("季節の野菜が一つになった4種の栄養満点カレー。ラクラクカレーオリジナル商品でベジタリアンの方にオススメです", item.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("12.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest13() {

		Item item = itemService.showDetail(13);

		assertEquals(13, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("バラエティー４", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("あらびきスライス牛肉とイタリアンチーズを、トマトソースと特製マヨソースの2種類のソースで召し上がって頂く商品です", item.getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("13.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest14() {

		Item item = itemService.showDetail(14);

		assertEquals(14, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("えびナスカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("デミグラスソースでじっくり煮込んだ旨味たっぷりのえびとナスのカレー", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2980, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4460, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("14.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest15() {

		Item item = itemService.showDetail(15);

		assertEquals(15, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("Family４", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクピザ自慢「特うまプルコギ」定番「デラックス」お子様に人気「ツナマイルド」女性に好評「チーズ＆チーズ」の４種のおいしさを贅沢に組み合わせました",
				item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("15.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest16() {

		Item item = itemService.showDetail(16);

		assertEquals(16, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("シンプルイズベスト", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("人気ナンバー１！魚介の旨みたっぷり！人気の海の幸と、野菜のリッチなおいしさ", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("16.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest17() {

		Item item = itemService.showDetail(17);

		assertEquals(17, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("学芸会カレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("みんな大好き！学芸会で作るような味を再現！定番のおいしさを味わえます", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("17.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void showDetailTest18() {

		Item item = itemService.showDetail(18);

		assertEquals(18, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("黄金に輝くチキンカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーが黄金に輝く、超高級鶏肉を使用したカレーです", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("18.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void updateDeleteFlagAndfindAllByPriceTest1() {

		Item item1 = itemService.showDetail(1);
		Item item2 = itemService.showDetail(3);
		Item item3 = itemService.showDetail(4);
		Item item4 = itemService.showDetail(8);

		assertFalse(item1.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item2.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item3.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item4.isDeleted(), "削除フラグが期待される結果と異なります。");

		itemService.updateDeleteFlag(true, 1);
		itemService.updateDeleteFlag(true, 3);
		itemService.updateDeleteFlag(true, 4);
		itemService.updateDeleteFlag(true, 8);

		item1 = itemService.showDetail(1);
		item2 = itemService.showDetail(3);
		item3 = itemService.showDetail(4);
		item4 = itemService.showDetail(8);

		assertTrue(item1.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item2.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item3.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item4.isDeleted(), "削除フラグが期待される結果と異なります。");

		List<Item> itemList = itemService.findAllByPrice();

		assertEquals(14, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(2, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("ポークポークカレー・ミート", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です", itemList.get(0).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("2.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(5, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("とんかつカレーラーメン", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーはライスだけではありません。ラクラクピザが開発したカレーラーメンは絶品の美味しさ！", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("5.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(13, itemList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("バラエティー４", itemList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("あらびきスライス牛肉とイタリアンチーズを、トマトソースと特製マヨソースの2種類のソースで召し上がって頂く商品です", itemList.get(2).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("13.jpg", itemList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(2).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, itemList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", itemList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", itemList.get(3).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(3).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(3).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", itemList.get(3).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(3).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(12, itemList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("季節の野菜カレー", itemList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals("季節の野菜が一つになった4種の栄養満点カレー。ラクラクカレーオリジナル商品でベジタリアンの方にオススメです", itemList.get(4).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(4).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(4).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("12.jpg", itemList.get(4).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(4).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(15, itemList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("Family４", itemList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクピザ自慢「特うまプルコギ」定番「デラックス」お子様に人気「ツナマイルド」女性に好評「チーズ＆チーズ」の４種のおいしさを贅沢に組み合わせました",
				itemList.get(5).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(5).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(5).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("15.jpg", itemList.get(5).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(5).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(17, itemList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("学芸会カレー", itemList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals("みんな大好き！学芸会で作るような味を再現！定番のおいしさを味わえます", itemList.get(6).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(6).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(6).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("17.jpg", itemList.get(6).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(6).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(7, itemList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("濃厚Gorgeous4", itemList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals("こだわりのソースで、旨みとコクを堪能！濃厚Gorgeous4とは、動物由来の原材料を使用していないカレーです。ベジタリアンの方にオススメです",
				itemList.get(7).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2570, itemList.get(7).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3780, itemList.get(7).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("7.jpg", itemList.get(7).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(7).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(9, itemList.get(8).getId(), "IDが期待される結果と異なります。");
		assertEquals("Charity4", itemList.get(8).getName(), "名前が期待される結果と異なります。");
		assertEquals("にんにくとトマトの旨みが効いたスパイスカレー。食べると思わず元気が出るラクラクカレーの自信作", itemList.get(8).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(8).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(8).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("9.jpg", itemList.get(8).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(8).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, itemList.get(9).getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", itemList.get(9).getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", itemList.get(9).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(9).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(9).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", itemList.get(9).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(9).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(16, itemList.get(10).getId(), "IDが期待される結果と異なります。");
		assertEquals("シンプルイズベスト", itemList.get(10).getName(), "名前が期待される結果と異なります。");
		assertEquals("人気ナンバー１！魚介の旨みたっぷり！人気の海の幸と、野菜のリッチなおいしさ", itemList.get(10).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(10).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(10).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("16.jpg", itemList.get(10).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(10).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, itemList.get(11).getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", itemList.get(11).getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", itemList.get(11).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(11).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(11).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", itemList.get(11).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(11).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(18, itemList.get(12).getId(), "IDが期待される結果と異なります。");
		assertEquals("黄金に輝くチキンカレー", itemList.get(12).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーが黄金に輝く、超高級鶏肉を使用したカレーです", itemList.get(12).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(12).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(12).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("18.jpg", itemList.get(12).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(12).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(14, itemList.get(13).getId(), "IDが期待される結果と異なります。");
		assertEquals("えびナスカレー", itemList.get(13).getName(), "名前が期待される結果と異なります。");
		assertEquals("デミグラスソースでじっくり煮込んだ旨味たっぷりのえびとナスのカレー", itemList.get(13).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2980, itemList.get(13).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4460, itemList.get(13).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("14.jpg", itemList.get(13).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(13).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void updateDeleteFlagAndfindAllByPriceTest2() {

		Item item1 = itemService.showDetail(1);
		Item item2 = itemService.showDetail(2);
		Item item3 = itemService.showDetail(3);
		Item item4 = itemService.showDetail(4);
		Item item5 = itemService.showDetail(5);
		Item item6 = itemService.showDetail(6);
		Item item7 = itemService.showDetail(7);
		Item item8 = itemService.showDetail(8);
		Item item9 = itemService.showDetail(9);
		Item item10 = itemService.showDetail(10);
		Item item11 = itemService.showDetail(11);
		Item item12 = itemService.showDetail(12);
		Item item13 = itemService.showDetail(13);
		Item item14 = itemService.showDetail(14);
		Item item15 = itemService.showDetail(15);
		Item item16 = itemService.showDetail(16);
		Item item17 = itemService.showDetail(17);
		Item item18 = itemService.showDetail(18);

		assertFalse(item1.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item2.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item3.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item4.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item5.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item6.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item7.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item8.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item9.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item10.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item11.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item12.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item13.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item14.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item15.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item16.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item17.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertFalse(item18.isDeleted(), "削除フラグが期待される結果と異なります。");

		itemService.updateDeleteFlag(true, 1);
		itemService.updateDeleteFlag(true, 2);
		itemService.updateDeleteFlag(true, 3);
		itemService.updateDeleteFlag(true, 4);
		itemService.updateDeleteFlag(true, 5);
		itemService.updateDeleteFlag(true, 6);
		itemService.updateDeleteFlag(true, 7);
		itemService.updateDeleteFlag(true, 8);
		itemService.updateDeleteFlag(true, 9);
		itemService.updateDeleteFlag(true, 10);
		itemService.updateDeleteFlag(true, 11);
		itemService.updateDeleteFlag(true, 12);
		itemService.updateDeleteFlag(true, 13);
		itemService.updateDeleteFlag(true, 14);
		itemService.updateDeleteFlag(true, 15);
		itemService.updateDeleteFlag(true, 16);
		itemService.updateDeleteFlag(true, 17);
		itemService.updateDeleteFlag(true, 18);

		item1 = itemService.showDetail(1);
		item2 = itemService.showDetail(2);
		item3 = itemService.showDetail(3);
		item4 = itemService.showDetail(4);
		item5 = itemService.showDetail(5);
		item6 = itemService.showDetail(6);
		item7 = itemService.showDetail(7);
		item8 = itemService.showDetail(8);
		item9 = itemService.showDetail(9);
		item10 = itemService.showDetail(10);
		item11 = itemService.showDetail(11);
		item12 = itemService.showDetail(12);
		item13 = itemService.showDetail(13);
		item14 = itemService.showDetail(14);
		item15 = itemService.showDetail(15);
		item16 = itemService.showDetail(16);
		item17 = itemService.showDetail(17);
		item18 = itemService.showDetail(18);

		assertTrue(item1.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item2.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item3.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item4.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item5.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item6.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item7.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item8.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item9.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item10.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item11.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item12.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item13.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item14.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item15.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item16.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item17.isDeleted(), "削除フラグが期待される結果と異なります。");
		assertTrue(item18.isDeleted(), "削除フラグが期待される結果と異なります。");

		List<Item> itemList = itemService.findAllByPrice();

		assertEquals(0, itemList.size(), "件数が期待される結果と異なります。");
	}

	@Test
	public void updateItemTest() {

		Item item = itemService.showDetail(1);

		assertEquals(1, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");

		item.setName("カツカレーカツカレー");
		item.setDescription("勝つカレー");
		item.setPriceM(1500);
		item.setPriceL(2000);

		itemService.updateItem(item);

		item = itemService.showDetail(1);

		assertEquals(1, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレーカツカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("勝つカレー", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1500, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2000, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");

		item.setName("カツカレー");
		item.setDescription("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です");
		item.setPriceM(1490);
		item.setPriceL(2570);

		itemService.updateItem(item);

		item = itemService.showDetail(1);

		assertEquals(1, item.getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", item.getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", item.getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, item.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, item.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", item.getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(item.isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findAllByPrice2Test() {

		List<Item> itemList = itemService.findAllByPrice2();

		assertEquals(18, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(14, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("えびナスカレー", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("デミグラスソースでじっくり煮込んだ旨味たっぷりのえびとナスのカレー", itemList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2980, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4460, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("14.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(9, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("Charity4", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("にんにくとトマトの旨みが効いたスパイスカレー。食べると思わず元気が出るラクラクカレーの自信作", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("9.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, itemList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", itemList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", itemList.get(2).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", itemList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(2).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(16, itemList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("シンプルイズベスト", itemList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals("人気ナンバー１！魚介の旨みたっぷり！人気の海の幸と、野菜のリッチなおいしさ", itemList.get(3).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(3).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(3).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("16.jpg", itemList.get(3).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(3).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, itemList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", itemList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", itemList.get(4).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(4).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(4).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", itemList.get(4).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(4).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(18, itemList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("黄金に輝くチキンカレー", itemList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーが黄金に輝く、超高級鶏肉を使用したカレーです", itemList.get(5).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(5).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(5).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("18.jpg", itemList.get(5).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(5).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(7, itemList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("濃厚Gorgeous4", itemList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals("こだわりのソースで、旨みとコクを堪能！濃厚Gorgeous4とは、動物由来の原材料を使用していないカレーです。ベジタリアンの方にオススメです",
				itemList.get(6).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2570, itemList.get(6).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3780, itemList.get(6).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("7.jpg", itemList.get(6).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(6).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(15, itemList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("Family４", itemList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクピザ自慢「特うまプルコギ」定番「デラックス」お子様に人気「ツナマイルド」女性に好評「チーズ＆チーズ」の４種のおいしさを贅沢に組み合わせました",
				itemList.get(7).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(7).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(7).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("15.jpg", itemList.get(7).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(7).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(17, itemList.get(8).getId(), "IDが期待される結果と異なります。");
		assertEquals("学芸会カレー", itemList.get(8).getName(), "名前が期待される結果と異なります。");
		assertEquals("みんな大好き！学芸会で作るような味を再現！定番のおいしさを味わえます", itemList.get(8).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(8).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(8).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("17.jpg", itemList.get(8).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(8).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(8, itemList.get(9).getId(), "IDが期待される結果と異なります。");
		assertEquals("カレーうどん", itemList.get(9).getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクカレー自慢のカレーに魚介のダシ、ローストオニオンのコクが加わった絶妙なスープをお楽しみください", itemList.get(9).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(9).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(9).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("8.jpg", itemList.get(9).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(9).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(13, itemList.get(10).getId(), "IDが期待される結果と異なります。");
		assertEquals("バラエティー４", itemList.get(10).getName(), "名前が期待される結果と異なります。");
		assertEquals("あらびきスライス牛肉とイタリアンチーズを、トマトソースと特製マヨソースの2種類のソースで召し上がって頂く商品です", itemList.get(10).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(10).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(10).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("13.jpg", itemList.get(10).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(10).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, itemList.get(11).getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", itemList.get(11).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", itemList.get(11).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(11).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(11).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", itemList.get(11).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(11).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(12, itemList.get(12).getId(), "IDが期待される結果と異なります。");
		assertEquals("季節の野菜カレー", itemList.get(12).getName(), "名前が期待される結果と異なります。");
		assertEquals("季節の野菜が一つになった4種の栄養満点カレー。ラクラクカレーオリジナル商品でベジタリアンの方にオススメです", itemList.get(12).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(12).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(12).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("12.jpg", itemList.get(12).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(12).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(5, itemList.get(13).getId(), "IDが期待される結果と異なります。");
		assertEquals("とんかつカレーラーメン", itemList.get(13).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーはライスだけではありません。ラクラクピザが開発したカレーラーメンは絶品の美味しさ！", itemList.get(13).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(13).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(13).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("5.jpg", itemList.get(13).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(13).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, itemList.get(14).getId(), "IDが期待される結果と異なります。");
		assertEquals("味噌カツカレー", itemList.get(14).getName(), "名前が期待される結果と異なります。");
		assertEquals("マイルドな味付けのカレーに大きくカットした味噌カツをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です", itemList.get(14).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(14).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(14).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("4.jpg", itemList.get(14).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(14).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(1, itemList.get(15).getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", itemList.get(15).getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", itemList.get(15).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(15).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(15).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", itemList.get(15).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(15).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(2, itemList.get(16).getId(), "IDが期待される結果と異なります。");
		assertEquals("ポークポークカレー・ミート", itemList.get(16).getName(), "名前が期待される結果と異なります。");
		assertEquals("グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です", itemList.get(16).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(16).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(16).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("2.jpg", itemList.get(16).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(16).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(3, itemList.get(17).getId(), "IDが期待される結果と異なります。");
		assertEquals("牛すじカレー", itemList.get(17).getName(), "名前が期待される結果と異なります。");
		assertEquals("トロトロの牛すじとネギの風味が格別な味わいシンプルなカレーです！", itemList.get(17).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(17).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(17).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("3.jpg", itemList.get(17).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(17).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findAllByPrice3Test() {

		List<Item> itemList = itemService.findAllByPrice3();

		assertEquals(18, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(1, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", itemList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(2, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("ポークポークカレー・ミート", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("2.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(3, itemList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("牛すじカレー", itemList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("トロトロの牛すじとネギの風味が格別な味わいシンプルなカレーです！", itemList.get(2).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("3.jpg", itemList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(2).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, itemList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("味噌カツカレー", itemList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals("マイルドな味付けのカレーに大きくカットした味噌カツをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です", itemList.get(3).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(3).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(3).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("4.jpg", itemList.get(3).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(3).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(5, itemList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("とんかつカレーラーメン", itemList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーはライスだけではありません。ラクラクピザが開発したカレーラーメンは絶品の美味しさ！", itemList.get(4).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(4).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(4).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("5.jpg", itemList.get(4).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(4).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, itemList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", itemList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", itemList.get(5).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(5).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(5).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", itemList.get(5).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(5).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(7, itemList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("濃厚Gorgeous4", itemList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals("こだわりのソースで、旨みとコクを堪能！濃厚Gorgeous4とは、動物由来の原材料を使用していないカレーです。ベジタリアンの方にオススメです",
				itemList.get(6).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2570, itemList.get(6).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3780, itemList.get(6).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("7.jpg", itemList.get(6).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(6).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(8, itemList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("カレーうどん", itemList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクカレー自慢のカレーに魚介のダシ、ローストオニオンのコクが加わった絶妙なスープをお楽しみください", itemList.get(7).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(7).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(7).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("8.jpg", itemList.get(7).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(7).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(9, itemList.get(8).getId(), "IDが期待される結果と異なります。");
		assertEquals("Charity4", itemList.get(8).getName(), "名前が期待される結果と異なります。");
		assertEquals("にんにくとトマトの旨みが効いたスパイスカレー。食べると思わず元気が出るラクラクカレーの自信作", itemList.get(8).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(8).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(8).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("9.jpg", itemList.get(8).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(8).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, itemList.get(9).getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", itemList.get(9).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", itemList.get(9).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(9).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(9).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", itemList.get(9).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(9).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, itemList.get(10).getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", itemList.get(10).getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", itemList.get(10).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(10).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(10).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", itemList.get(10).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(10).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(12, itemList.get(11).getId(), "IDが期待される結果と異なります。");
		assertEquals("季節の野菜カレー", itemList.get(11).getName(), "名前が期待される結果と異なります。");
		assertEquals("季節の野菜が一つになった4種の栄養満点カレー。ラクラクカレーオリジナル商品でベジタリアンの方にオススメです", itemList.get(11).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(11).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(11).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("12.jpg", itemList.get(11).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(11).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(13, itemList.get(12).getId(), "IDが期待される結果と異なります。");
		assertEquals("バラエティー４", itemList.get(12).getName(), "名前が期待される結果と異なります。");
		assertEquals("あらびきスライス牛肉とイタリアンチーズを、トマトソースと特製マヨソースの2種類のソースで召し上がって頂く商品です", itemList.get(12).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(12).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(12).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("13.jpg", itemList.get(12).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(12).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(14, itemList.get(13).getId(), "IDが期待される結果と異なります。");
		assertEquals("えびナスカレー", itemList.get(13).getName(), "名前が期待される結果と異なります。");
		assertEquals("デミグラスソースでじっくり煮込んだ旨味たっぷりのえびとナスのカレー", itemList.get(13).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2980, itemList.get(13).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4460, itemList.get(13).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("14.jpg", itemList.get(13).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(13).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(15, itemList.get(14).getId(), "IDが期待される結果と異なります。");
		assertEquals("Family４", itemList.get(14).getName(), "名前が期待される結果と異なります。");
		assertEquals("ラクラクピザ自慢「特うまプルコギ」定番「デラックス」お子様に人気「ツナマイルド」女性に好評「チーズ＆チーズ」の４種のおいしさを贅沢に組み合わせました",
				itemList.get(14).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(14).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(14).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("15.jpg", itemList.get(14).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(14).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(16, itemList.get(15).getId(), "IDが期待される結果と異なります。");
		assertEquals("シンプルイズベスト", itemList.get(15).getName(), "名前が期待される結果と異なります。");
		assertEquals("人気ナンバー１！魚介の旨みたっぷり！人気の海の幸と、野菜のリッチなおいしさ", itemList.get(15).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(15).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(15).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("16.jpg", itemList.get(15).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(15).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(17, itemList.get(16).getId(), "IDが期待される結果と異なります。");
		assertEquals("学芸会カレー", itemList.get(16).getName(), "名前が期待される結果と異なります。");
		assertEquals("みんな大好き！学芸会で作るような味を再現！定番のおいしさを味わえます", itemList.get(16).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2440, itemList.get(16).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3650, itemList.get(16).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("17.jpg", itemList.get(16).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(16).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(18, itemList.get(17).getId(), "IDが期待される結果と異なります。");
		assertEquals("黄金に輝くチキンカレー", itemList.get(17).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレーが黄金に輝く、超高級鶏肉を使用したカレーです", itemList.get(17).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(17).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(17).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("18.jpg", itemList.get(17).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(17).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByItemNameTest1() {

		List<Item> itemList = itemService.findByItemName("カツカレー");

		assertEquals(3, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(1, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", itemList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("味噌カツカレー", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("マイルドな味付けのカレーに大きくカットした味噌カツをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("4.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, itemList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", itemList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", itemList.get(2).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", itemList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(2).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByItemNameTest2() {

		List<Item> itemList = itemService.findByItemName("ドリア");

		assertEquals(2, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(10, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", itemList.get(0).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", itemList.get(1).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByItemName2Test1() {

		List<Item> itemList = itemService.findByItemName2("カツカレー");

		assertEquals(3, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(6, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", itemList.get(0).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("味噌カツカレー", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("マイルドな味付けのカレーに大きくカットした味噌カツをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("4.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(1, itemList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", itemList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", itemList.get(2).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", itemList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(2).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByItemName2Test2() {

		List<Item> itemList = itemService.findByItemName2("ドリア");

		assertEquals(2, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(11, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", itemList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(10, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByItemName3Test1() {

		List<Item> itemList = itemService.findByItemName3("カツカレー");

		assertEquals(3, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(1, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("カツカレー", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("食べると勝負に勝てると言われる勝つカレー。ラクラクカレー定番の１品です", itemList.get(0).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(1490, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("1.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(4, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("味噌カツカレー", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("マイルドな味付けのカレーに大きくカットした味噌カツをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です", itemList.get(1).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(1900, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2980, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("4.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(6, itemList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("ヒレカツカレー", itemList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals("やわらかくあっさりとしたヒレ肉を上質な衣で包み込みました。4種類の濃厚な味わいが一つで楽しめるカレーです", itemList.get(2).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("6.jpg", itemList.get(2).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(2).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@Test
	public void findByItemName3Test2() {

		List<Item> itemList = itemService.findByItemName3("ドリア");

		assertEquals(2, itemList.size(), "件数が期待される結果と異なります。");

		assertEquals(10, itemList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("ほうれん草のカレードリア", itemList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals("カレードリアの王道！ホワイトソースとカレーのダブルソースとなす、ほうれん草、チーズのおいしい組み合わせ", itemList.get(0).getDescription(),
				"説明が期待される結果と異なります。");
		assertEquals(2160, itemList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, itemList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("10.jpg", itemList.get(0).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(0).isDeleted(), "削除フラグが期待される結果と異なります。");

		assertEquals(11, itemList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("Specialドリア4", itemList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals("ホワイトソースとカレーのダブルソースにハンバーグを合わせました", itemList.get(1).getDescription(), "説明が期待される結果と異なります。");
		assertEquals(2700, itemList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(4050, itemList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("11.jpg", itemList.get(1).getImagePath(), "画像パスが期待される結果と異なります。");
		assertFalse(itemList.get(1).isDeleted(), "削除フラグが期待される結果と異なります。");
	}

	@AfterEach
	public void after() {

		String sql = "UPDATE items SET deleted = false";

		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql, param);
	}

}
