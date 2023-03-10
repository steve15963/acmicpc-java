package p13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class p13023 {
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		//인접 행렬로 시도했으나 실패한 흔적
		//boolean RShip[][] = new boolean[N][N];
		
		//인접 리스트.
		ArrayList<Integer> RShip[] = new ArrayList[N];
        boolean visit[] = new boolean[N];
		for(int i = 0; i < N; i++) {
			RShip[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int	to = Integer.parseInt(st.nextToken());
			//RShip[from][to] = RShip[to][from] = true;
			RShip[from].add(to);
			RShip[to].add(from);
		}
		// 시작 노드를 변경하면서.
		for(int i = 0; i < N; i++) {
			//boolean visit[] = new boolean[N];
			visit[i] = true;
			if(DFS(0,i,RShip,visit,N)) break;
            visit[i] = false;
		}
		
		System.out.println(answer);
		
	}
	
	private static boolean DFS(int cnt,int from, ArrayList<Integer>[] rShip, boolean[] visit,int N) {
		// 4개 이상이 탐색에 성공할 경우
		if(cnt == 4) {
			answer = 1;
			return true;
		}
		for(int to : rShip[from]) {
			if(!visit[to]) {
				visit[to] = true;
				if(DFS(cnt + 1, to, rShip, visit, N)) return true;
				visit[to] = false;
			}
		}
		//인접 행렬 실패 흔적
//		for(int i = 0 ; i < N; i++) {
//			if(rShip[A][i] && !visit[i]) {
//				visit[i] = true;
//				if(DFS(cnt + 1, i, rShip, visit, N)) return true;
//				visit[i] = false;
//			}
//		}
		return false;
	}
}
