package taskaji;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Kadai {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//立候補者数
		int candidateNum = sc.nextInt();

		//誰も支持しない有権者数
		int voterNum = sc.nextInt();

		//演説回数
		int speachCnt = sc.nextInt();

		HashMap<Integer,Integer> persons =  new HashMap<Integer,Integer>();

		//候補者の数だけハッシュマップを作成
		for (Integer p = 1; p <= candidateNum; p ++){
			persons.put(p,0);
		}

		//講演の実施
		for (int s = 0; s < speachCnt; s ++){

			//講演する人を取得
			int speachPerson = sc.nextInt();

			//講演する人の支持者の数を取得
			int addVoter = persons.get(speachPerson);

			//誰も支持しない有権者数が０以外の時
			if(voterNum != 0){

				//講演する人に支持者を１人追加
				voterNum--;
				addVoter++;
			}

			for (int p = 1; p <= candidateNum; p ++){

				//講演する人以外の支持者の数
				int fan = persons.get(p);

				if (fan != 0 && p != speachPerson ){

					//講演する人以外の支持者を１人減らす
					persons.put(p, --fan);

					//講演する人に支持者を１人追加
					addVoter++;
				}
			}

				//講演する人の支持者の数を更新
		    	persons.put(speachPerson, addVoter);
		}

		//支持者の数でソート
		List<Map.Entry> entries = new ArrayList<Map.Entry>(persons.entrySet());
		Collections.sort(entries, new Comparator(){
			public int compare(Object o1, Object o2){
			Map.Entry e1 =(Map.Entry)o1;
			Map.Entry e2 =(Map.Entry)o2;
			return ((Integer)e1.getValue()).compareTo((Integer)e2.getValue());
			}
		});

		//支持者の最大値を取得
		Object maxperson = entries.get(--candidateNum).getValue();

		//支持者が最も多い立候補者を表示（複数可）
		for (Map.Entry entry : entries) {
			if(maxperson.equals(entry.getValue())) {
				System.out.println(entry.getKey());

			}
		}
		sc.close();
    }
}