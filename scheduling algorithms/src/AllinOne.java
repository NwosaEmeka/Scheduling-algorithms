import java.util.Scanner;
public class AllinOne {
	static Scanner input = new Scanner(System.in);
	public static void main (String[]args){
		
		int num,n;
		String s;
		System.out.print("Enter number of process: ");
		num = input.nextInt();
		int[] burst= new int[num];
		int[]remain = new int[num];
		int[]waiting = new int[num];
		int[]tat = new int[num];
		char[]name = new char[num];
	
		
		for(int i =0; i<num;i++){
			System.out.print("Enter burst size of P"+(i+1)+": ");
			burst[i] = remain[i] = input.nextInt();
			System.out.print("Enter name of P"+(i+1)+": ");
			name[i] = name[i] = input.next().charAt(0);
		}
		System.out.println("\nSELECTION: \n1: ROUND ROBIN\n2: SHORTEST JOB FIRST\n3: FIRST COME FIRST SERVE\n4. PRIORITY\n");
		do{
			System.out.print("Select a number");
			n = input.nextInt();
			if(n ==1){
				RR(burst,remain,name,waiting,tat,num);
			}else if (n ==2){
				SJF(burst,name,waiting,tat,num);
			}else if (n ==3){
				FCFS(burst,name,waiting,tat,num);
			}else if (n==4){
				PP(burst,name,waiting,tat,num);
			}
			System.out.println("\nEnter y to continue or n to terminate");
			s = input.next();
		}while(s.toLowerCase().charAt(0)=='y');
	}
	
	//ROUND ROBBIN
	public static void RR(int[]burst, int[]remain, char[]name,int[]waiting,int[]tat,int num){
		int q, totalwt=0,totaltat=0,flag=0;
		System.out.println("Enter quntum:");
		q = input.nextInt();
		System.out.println("\n------------- RESULT FOR ROUND ROBIN---------------\n");
		System.out.print("\nGantt chart: ");
		do{
			flag =0;
			for(int i=0;i<num;i++){
				if(remain[i]>=q){
					for(int k =0; k<q;k++){
	                  System.out.print(name[i]+" ");
	            	  }
	                  for(int j=0;j<num;j++){
	                       if(j==i)
	                       remain[i]=remain[i]-q;
	                       else if(remain[j]>0)
	                       waiting[j]+=q;
	                 }
	             }
	              else if(remain[i]>0){
	            	  for(int k =0; k <remain[i];k++){
	              System.out.print(name[i]+" ");
	            	  }
	              for(int j=0;j<num;j++){
	                 if(j==i)
	                 remain[i]=0;
	                 else if(remain[j]>0)
	                 waiting[j]+=remain[i];
	               }
	            }
	         }
	             for(int i=0;i<num;i++)
	                 if(remain[i]>0)
	                 flag=1;
	                }while(flag==1);
		
		System.out.println ("\n\nProcess \tWiting time\tTurnAround Time\n-------------------------------------------------");
		
		for(int i =0; i < num;i++){
			tat[i]=waiting[i]+burst[i];
			totalwt = totalwt + waiting[i];
			totaltat = totaltat +tat[i];
			System.out.println("Process "+name[i]+ "|\t"+waiting[i] +"\t|\t"+tat[i]);
			}
		double averageWt = (double)totalwt/num;
		double averageTat = (double)totaltat/num;
		System.out.printf("\n\nAverage TurnAroundTime: %.2f\nAverage Waiting time for %01d processes: %.2f\n",averageWt,num,averageTat);
		System.out.println("------------------------------------------------------------");
	}
	
	//FIRST COME FIRST SERVE
	public static void FCFS(int[]burst, char[]name, int[]waiting, int[]tat,int num){
		int x =0,totalwt=0,totaltat=0;
		System.out.println("\n------------- RESULT FOR FIRST COME FIRST SERVE---------------\n");
		System.out.println ("Process \tWiting time\tTurnAround Time\n-------------------------------------------------");
		for(int i =0; i <num; i++){
			x = x + burst[i];
			tat[i] = x;
			waiting[i] = tat[i] - burst[i];
			System.out.println("Process "+name[i]+ "|\t"+waiting[i] +" \t|\t "+tat[i]);
			 totalwt = totalwt + waiting[i];
			 totaltat = totaltat +tat[i];
		}
		System.out.print("\nGantt chart: ");
		for(int i =0; i<num; i++){
			for (int j = 0; j<burst[i]; j++){
				System.out.print(name[i] +" ");
			}
		}
		double averageWt = (double)totalwt/num;
		double averageTat = (double)totaltat/num;
		System.out.printf("\n\nAverage TurnAroundTime: %.2f\nAverage Waiting time for %01d processes: %.2f\n",averageWt,num,averageTat);
		System.out.println("------------------------------------------------------------");
	}
	
	//SHORTEST JOB FIRST
    public static void SJF(int[]burst, char[]name, int[]waiting, int[]tat,int num){
		int x =0,totalwt=0,totaltat=0,temp;
		int[]nburst = new int [num];
		char[]nname = new char [num];
		char temp1;
		for(int k=0; k < num; k++){
			nburst[k] = burst[k] ;
			nname[k] = name[k];
		}
		//COMPARE THE BURT TIME
		for(int i =0; i <num; i++){
			for (int j =i+1; j<num; j++){
				if(nburst[j] < nburst[i]){
					temp = nburst[i];
					temp1 = nname[i];
					nburst[i] = nburst[j];
					nname[i] = nname[j];
					nburst[j] = temp;
					nname[j] =temp1;
				}
			}
		}
		System.out.println("\n------------- RESULT FOR SHORTEST JOB FIRST---------------\n");
		System.out.println ("Process \tWiting time\tTurnAround Time\n-------------------------------------------------");
		for(int i =0; i <num; i++){
			x = x + nburst[i];
			tat[i] = x;
			waiting[i] = tat[i] - nburst[i];
			System.out.println("Process "+nname[i]+ "|\t"+waiting[i] +" \t|\t "+tat[i]);
			 totalwt = totalwt + waiting[i];
			 totaltat = totaltat +tat[i];
		}
		System.out.print("\nGantt chart: ");
		for(int i =0; i<num; i++){
			for (int j = 0; j<nburst[i]; j++){
				System.out.print(nname[i] +" ");
			}
		}
		double averageWt = (double)totalwt/num;
		double averageTat = (double)totaltat/num;
		System.out.printf("\n\nAverage TurnAroundTime: %.2f\nAverage Waiting time for %01d processes: %.2f\n",averageWt,num,averageTat);
		System.out.println("------------------------------------------------------------");
	}
    
    //PRIORITY SCHEDULING
    public static void PP(int[]burst, char[]name, int[]waiting, int[]tat,int num){
    	int[]priority = new int[num];
    	int[]nburst = new int[num];
    	char[]nname = new char[num];
    	int x =0,totalwt=0,totaltat=0,temp;
    	char temp1;
    	
    	System.out.println ("1 = Highest priority");
    	for(int k =0; k <num; k++){
    		System.out.print("Enter priority for P"+(k+1)+": ");
    		priority[k] = input.nextInt();
    		nburst[k] = burst[k] ;
			nname[k] = name[k];
    	}
    	System.out.println("\n------------- RESULT FOR PRIORITY SCHEDULING---------------\n");
		System.out.println ("Process \tWiting time\tTurnAround Time\n-------------------------------------------------");
    	for(int i = 0; i <num; i++){
    		for(int j =i+1; j<num; j++){
    			if(priority[j] < priority[i]){
    				temp = priority[i];
    				temp = nburst[i];
    				temp1 = nname[i];
    				priority[i] = priority[j];
    				nburst[i]= nburst[j];
    				nname[i]= nname[j];
    				priority[j] = temp;
    				nburst[j] =temp;
    				nname[j] = temp1;
    			}
    		}
    	}
    	for(int i = 0; i< num; i++){
    		x = x + nburst[i];
    		tat[i] = x;
    		waiting[i] = tat[i]-nburst[i];
    		System.out.println("Process "+nname[i]+ "|\t"+waiting[i] +" \t|\t "+tat[i]);
			 totalwt = totalwt + waiting[i];
			 totaltat = totaltat +tat[i];
    	}
    	System.out.print("\nGantt chart: ");
		for(int i =0; i<num; i++){
			for (int j = 0; j<nburst[i]; j++){
				System.out.print(nname[i] +" ");
			}
		}
		double averageWt = (double)totalwt/num;
		double averageTat = (double)totaltat/num;
		System.out.printf("\n\nAverage TurnAroundTime: %.2f\nAverage Waiting time for %01d processes: %.2f\n",averageWt,num,averageTat);
		System.out.println("------------------------------------------------------------");
    }
}

