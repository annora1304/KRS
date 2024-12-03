import java.util.Scanner;
class Course {
    String kodeMK;
    String namaMK;
    int sks;

    Course(String kodeMK, String namaMK, int sks) {
        this.kodeMK = kodeMK;
        this.namaMK = namaMK;
        this.sks = sks;
    }
}
class Student {
    String nama;
    String nim;
    Course[] courses;
    int courseCount;
    int totalSKS;

    Student(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
        this.courses = new Course[10]; 
        this.courseCount = 0;
        this.totalSKS = 0;
    }
    void addCourse(Course course) {
        if (courseCount < 10) {
            courses[courseCount++] = course;
            totalSKS += course.sks;
        }
    }
}
public class KRSManager {
    static Scanner scanner = new Scanner(System.in);
    static Student[] students = new Student[100]; 
    static int studentCount = 0;

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addStudentKRS();
                    break;
                case 2:
                    displayStudentKRS();
                    break;
                case 3:
                    analyzeKRSData();
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
    private static void showMainMenu() {
        System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
        System.out.println("1. Tambah Data KRS");
        System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
        System.out.println("3. Analisis Data KRS");
        System.out.println("4. Keluar");
        System.out.print("Pilih menu: ");
    }
    private static void addStudentKRS() {
        System.out.print("Nama Mahasiswa: ");
        String studentName = scanner.nextLine();
        System.out.print("NIM: ");
        String nim = scanner.nextLine();

        Student student = new Student(studentName, nim);

        while (true) {
            System.out.print("Kode Mata Kuliah: ");
            String kodeMK = scanner.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String namaMK = scanner.nextLine();
            System.out.print("Jumlah SKS (1-3): ");
            int sks = scanner.nextInt();
            scanner.nextLine(); 

            if (sks < 1 || sks > 3) {
                System.out.println("Jumlah SKS harus antara 1 dan 3.");
                continue;
            }

            if (student.totalSKS + sks > 24) {
                System.out.println("Total SKS melebihi batas (maksimal 24 SKS).");
                continue;
            }

            student.addCourse(new Course(kodeMK, namaMK, sks));

            System.out.print("Tambahkan mata kuliah lagi? (y/n): ");
            String addMore = scanner.nextLine();
            if (!addMore.equalsIgnoreCase("y")) {
                break;
            }
        }

        students[studentCount++] = student;
        System.out.println("Data KRS berhasil ditambahkan.");
    }

    private static void displayStudentKRS() {
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].nim.equals(nim)) {
                System.out.println("\nDaftar KRS Mahasiswa:");
                System.out.println("NIM: " + students[i].nim);
                System.out.println("Nama: " + students[i].nama);
                System.out.println("Kode MK\tNama Mata Kuliah\tSKS");
                for (int j = 0; j < students[i].courseCount; j++) {
                    System.out.println(students[i].courses[j].kodeMK + "\t" + students[i].courses[j].namaMK + "\t" + students[i].courses[j].sks);
                }
                System.out.println("Total SKS: " + students[i].totalSKS);
                return; 
            }
        }
    }
    private static void analyzeKRSData() {
            if (studentCount == 0) {
                System.out.println("Tidak ada data mahasiswa.");
                return;
            }
    
            int countLessThan20SKS = 0;
    
            for (int i = 0; i < studentCount; i++) {
                if (students[i].totalSKS < 20) {
                    countLessThan20SKS++;
                }
            }
    
            System.out.println("\n=== Analisis Data KRS Mahasiswa ===");
            System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + countLessThan20SKS);
        }
    
}