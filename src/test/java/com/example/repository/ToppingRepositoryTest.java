package com.example.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Topping;

@RunWith(SpringRunner.class)
@SpringBootTest
class ToppingRepositoryTest {

	@Autowired
	private ToppingRepository toppingRepository;

	@Test
	public void findAllTest() {

		List<Topping> toppingList = toppingRepository.findAll();

		assertEquals(28, toppingList.size(), "件数が期待される結果と異なります。");

		assertEquals(19, toppingList.get(0).getId(), "IDが期待される結果と異なります。");
		assertEquals("あらびきスライスソｰセｰジ", toppingList.get(0).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(0).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(0).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(6, toppingList.get(1).getId(), "IDが期待される結果と異なります。");
		assertEquals("アンチョビ", toppingList.get(1).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(1).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(1).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(4, toppingList.get(2).getId(), "IDが期待される結果と異なります。");
		assertEquals("イカ", toppingList.get(2).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(2).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(2).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(17, toppingList.get(3).getId(), "IDが期待される結果と異なります。");
		assertEquals("イタリアンソーセージ", toppingList.get(3).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(3).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(3).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(3, toppingList.get(4).getId(), "IDが期待される結果と異なります。");
		assertEquals("イタリアントマト", toppingList.get(4).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(4).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(4).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(7, toppingList.get(5).getId(), "IDが期待される結果と異なります。");
		assertEquals("エビ", toppingList.get(5).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(5).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(5).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(1, toppingList.get(6).getId(), "IDが期待される結果と異なります。");
		assertEquals("オニオン", toppingList.get(6).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(6).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(6).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(18, toppingList.get(7).getId(), "IDが期待される結果と異なります。");
		assertEquals("ガーリックスライス", toppingList.get(7).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(7).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(7).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(15, toppingList.get(8).getId(), "IDが期待される結果と異なります。");
		assertEquals("カマンベールチーズ", toppingList.get(8).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(8).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(8).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(21, toppingList.get(9).getId(), "IDが期待される結果と異なります。");
		assertEquals("グリーンアスパラ", toppingList.get(9).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(9).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(9).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(8, toppingList.get(10).getId(), "IDが期待される結果と異なります。");
		assertEquals("コーン", toppingList.get(10).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(10).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(10).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(28, toppingList.get(11).getId(), "IDが期待される結果と異なります。");
		assertEquals("チーズ増量", toppingList.get(11).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(11).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(11).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(2, toppingList.get(12).getId(), "IDが期待される結果と異なります。");
		assertEquals("ツナマヨ", toppingList.get(12).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(12).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(12).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(23, toppingList.get(13).getId(), "IDが期待される結果と異なります。");
		assertEquals("パイナップル", toppingList.get(13).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(13).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(13).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(24, toppingList.get(14).getId(), "IDが期待される結果と異なります。");
		assertEquals("ハラペーニョ", toppingList.get(14).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(14).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(14).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(22, toppingList.get(15).getId(), "IDが期待される結果と異なります。");
		assertEquals("パルメザンチーズ", toppingList.get(15).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(15).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(15).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(9, toppingList.get(16).getId(), "IDが期待される結果と異なります。");
		assertEquals("ピーマン", toppingList.get(16).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(16).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(16).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(27, toppingList.get(17).getId(), "IDが期待される結果と異なります。");
		assertEquals("ブラックオリーブ", toppingList.get(17).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(17).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(17).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(5, toppingList.get(18).getId(), "IDが期待される結果と異なります。");
		assertEquals("プルコギ", toppingList.get(18).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(18).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(18).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(10, toppingList.get(19).getId(), "IDが期待される結果と異なります。");
		assertEquals("フレッシュスライストマト", toppingList.get(19).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(19).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(19).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(16, toppingList.get(20).getId(), "IDが期待される結果と異なります。");
		assertEquals("フレッシュモッツァレラチーズ", toppingList.get(20).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(20).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(20).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(20, toppingList.get(21).getId(), "IDが期待される結果と異なります。");
		assertEquals("ブロッコリー", toppingList.get(21).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(21).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(21).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(11, toppingList.get(22).getId(), "IDが期待される結果と異なります。");
		assertEquals("ベーコン", toppingList.get(22).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(22).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(22).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(12, toppingList.get(23).getId(), "IDが期待される結果と異なります。");
		assertEquals("ペパロニ･サラミ", toppingList.get(23).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(23).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(23).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(26, toppingList.get(24).getId(), "IDが期待される結果と異なります。");
		assertEquals("ポテト", toppingList.get(24).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(24).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(24).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(25, toppingList.get(25).getId(), "IDが期待される結果と異なります。");
		assertEquals("もち", toppingList.get(25).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(25).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(25).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(13, toppingList.get(26).getId(), "IDが期待される結果と異なります。");
		assertEquals("熟成ベーコン", toppingList.get(26).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(26).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(26).getPriceL(), "Lサイズ価格が期待される結果と異なります。");

		assertEquals(14, toppingList.get(27).getId(), "IDが期待される結果と異なります。");
		assertEquals("特製マヨソース", toppingList.get(27).getName(), "名前が期待される結果と異なります。");
		assertEquals(200, toppingList.get(27).getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, toppingList.get(27).getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest1() {

		Topping topping = toppingRepository.load(1);

		assertEquals(1, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("オニオン", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest2() {

		Topping topping = toppingRepository.load(2);

		assertEquals(2, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ツナマヨ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest3() {

		Topping topping = toppingRepository.load(3);

		assertEquals(3, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("イタリアントマト", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest4() {

		Topping topping = toppingRepository.load(4);

		assertEquals(4, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("イカ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest5() {

		Topping topping = toppingRepository.load(5);

		assertEquals(5, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("プルコギ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest6() {

		Topping topping = toppingRepository.load(6);

		assertEquals(6, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("アンチョビ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest7() {

		Topping topping = toppingRepository.load(7);

		assertEquals(7, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("エビ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest8() {

		Topping topping = toppingRepository.load(8);

		assertEquals(8, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("コーン", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest9() {

		Topping topping = toppingRepository.load(9);

		assertEquals(9, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ピーマン", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest10() {

		Topping topping = toppingRepository.load(10);

		assertEquals(10, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("フレッシュスライストマト", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest11() {

		Topping topping = toppingRepository.load(11);

		assertEquals(11, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ベーコン", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest12() {

		Topping topping = toppingRepository.load(12);

		assertEquals(12, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ペパロニ･サラミ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest13() {

		Topping topping = toppingRepository.load(13);

		assertEquals(13, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("熟成ベーコン", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest14() {

		Topping topping = toppingRepository.load(14);

		assertEquals(14, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("特製マヨソース", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest15() {

		Topping topping = toppingRepository.load(15);

		assertEquals(15, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("カマンベールチーズ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest16() {

		Topping topping = toppingRepository.load(16);

		assertEquals(16, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("フレッシュモッツァレラチーズ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest17() {

		Topping topping = toppingRepository.load(17);

		assertEquals(17, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("イタリアンソーセージ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest18() {

		Topping topping = toppingRepository.load(18);

		assertEquals(18, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ガーリックスライス", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest19() {

		Topping topping = toppingRepository.load(19);

		assertEquals(19, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("あらびきスライスソｰセｰジ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest20() {

		Topping topping = toppingRepository.load(20);

		assertEquals(20, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ブロッコリー", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest21() {

		Topping topping = toppingRepository.load(21);

		assertEquals(21, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("グリーンアスパラ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest22() {

		Topping topping = toppingRepository.load(22);

		assertEquals(22, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("パルメザンチーズ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest23() {

		Topping topping = toppingRepository.load(23);

		assertEquals(23, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("パイナップル", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest24() {

		Topping topping = toppingRepository.load(24);

		assertEquals(24, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ハラペーニョ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest25() {

		Topping topping = toppingRepository.load(25);

		assertEquals(25, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("もち", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest26() {

		Topping topping = toppingRepository.load(26);

		assertEquals(26, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ポテト", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest27() {

		Topping topping = toppingRepository.load(27);

		assertEquals(27, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("ブラックオリーブ", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

	@Test
	public void loadTest28() {

		Topping topping = toppingRepository.load(28);

		assertEquals(28, topping.getId(), "IDが期待される結果と異なります。");
		assertEquals("チーズ増量", topping.getName(), "名前が期待される結果と異なります。");
		assertEquals(200, topping.getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, topping.getPriceL(), "Lサイズ価格が期待される結果と異なります。");
	}

}
