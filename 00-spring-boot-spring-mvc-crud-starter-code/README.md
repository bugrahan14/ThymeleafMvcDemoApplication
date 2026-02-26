# Employee Directory — Spring Boot MVC CRUD Uygulaması

Çalışan (Employee) yönetimi için tam CRUD (Create, Read, Update, Delete) işlemleri sunan, Spring Boot ve Spring MVC tabanlı web uygulaması.

---

## İçindekiler

- [Genel Bakış](#genel-bakış)
- [Teknolojiler](#teknolojiler)
- [Proje Yapısı](#proje-yapısı)
- [Gereksinimler](#gereksinimler)
- [Veritabanı Kurulumu](#veritabanı-kurulumu)
- [Yapılandırma](#yapılandırma)
- [Çalıştırma](#çalıştırma)
- [Uygulama Özellikleri](#uygulama-özellikleri)
- [Endpoint'ler ve Sayfalar](#endpointler-ve-sayfalar)
- [Mimari](#mimari)
- [Katkıda Bulunma](#katkıda-bulunma)

---

## Genel Bakış

Bu proje, **Employee Directory** (Çalışan Rehberi) senaryosu için geliştirilmiş örnek bir Spring Boot uygulamasıdır. Kullanıcılar çalışan ekleyebilir, listeleyebilir, güncelleyebilir ve silebilir. Arayüz Thymeleaf şablonları ve Bootstrap ile sunulur; veri katmanı Spring Data JPA ve MySQL ile yönetilir.

**Temel özellikler:**

- Çalışan listeleme (soyada göre sıralı)
- Yeni çalışan ekleme
- Mevcut çalışan güncelleme
- Çalışan silme (onay ile)
- Responsive, Bootstrap tabanlı arayüz

---

## Teknolojiler

| Teknoloji | Sürüm / Açıklama |
|-----------|------------------|
| **Java** | 25 |
| **Spring Boot** | 4.0.0 |
| **Spring Web MVC** | spring-boot-starter-webmvc |
| **Spring Data JPA** | spring-boot-starter-data-jpa |
| **Thymeleaf** | spring-boot-starter-thymeleaf |
| **MySQL** | mysql-connector-j (runtime) |
| **Spring Boot DevTools** | Geliştirme sırasında otomatik yeniden başlatma |
| **Maven** | Bağımlılık yönetimi ve derleme |

---

## Proje Yapısı

```
00-spring-boot-spring-mvc-crud-starter-code/
├── src/
│   ├── main/
│   │   ├── java/com/bugrahanERT/springboot/cruddemo/
│   │   │   ├── CruddemoApplication.java          # Ana uygulama sınıfı
│   │   │   ├── controller/
│   │   │   │   └── EmployeeController.java       # MVC controller
│   │   │   ├── dao/
│   │   │   │   └── EmployeeRepository.java       # JPA Repository
│   │   │   ├── entity/
│   │   │   │   └── Employee.java                 # JPA entity
│   │   │   └── service/
│   │   │       ├── EmployeeService.java          # Servis arayüzü
│   │   │       └── EmployeeServiceImpl.java     # Servis implementasyonu
│   │   └── resources/
│   │       ├── application.properties            # Uygulama ayarları
│   │       ├── static/
│   │       │   └── index.html                    # Ana sayfa yönlendirmesi
│   │       └── templates/
│   │           ├── bgr.html
│   │           └── employees/
│   │               ├── list-employees.html       # Çalışan listesi
│   │               └── employee-form.html       # Ekleme/Güncelleme formu
│   └── test/
│       └── java/.../CruddemoApplicationTests.java
├── sql-scripts/
│   └── employee-directory.sql                    # Veritabanı şeması ve örnek veri
├── pom.xml
├── mvnw, mvnw.cmd
└── README.md
```

---

## Gereksinimler

- **JDK 25** (veya projede tanımlı Java sürümü)
- **Maven 3.6+**
- **MySQL 8.x** (veya uyumlu bir sürüm)
- İsteğe bağlı: **IDE** (IntelliJ IDEA, Eclipse, VS Code vb.)

---

## Veritabanı Kurulumu

1. MySQL sunucunuzun çalıştığından emin olun.

2. `sql-scripts/employee-directory.sql` dosyasını çalıştırarak veritabanını ve tabloyu oluşturun:

   ```bash
   mysql -u root -p < sql-scripts/employee-directory.sql
   ```

   veya MySQL istemcisinde:

   ```sql
   source sql-scripts/employee-directory.sql;
   ```

3. Script şunları yapar:
   - `employee_directory` veritabanını oluşturur (yoksa)
   - `employee` tablosunu oluşturur (`id`, `first_name`, `last_name`, `email`)
   - Örnek 5 kayıt ekler

---

## Yapılandırma

Veritabanı bağlantısı `src/main/resources/application.properties` dosyasında tanımlıdır:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

Kendi ortamınıza göre şunları güncelleyin:

- **URL:** Sunucu adresi, port ve veritabanı adı
- **username / password:** MySQL kullanıcı adı ve şifresi

Örnek (farklı kullanıcı):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
spring.datasource.username=myuser
spring.datasource.password=mypassword
```

---

## Çalıştırma

### Maven ile

Proje kök dizininde:

```bash
./mvnw spring-boot:run
```

Windows’ta:

```bash
mvnw.cmd spring-boot:run
```

### JAR ile

```bash
./mvnw clean package
java -jar target/cruddemo-0.0.1-SNAPSHOT.jar
```

Uygulama varsayılan olarak **http://localhost:8080** adresinde çalışır. Tarayıcıda bu adrese gittiğinizde `index.html` sizi `/employees/list` sayfasına yönlendirir.

---

## Uygulama Özellikleri

- **Listeleme:** Tüm çalışanlar soyadına göre (A–Z) listelenir.
- **Ekleme:** "Add Employee" ile form açılır; ad, soyad ve e-posta girilerek kayıt oluşturulur.
- **Güncelleme:** Listede "Update" ile ilgili çalışanın formu açılır; değişiklikler kaydedilir.
- **Silme:** "Delete" tıklanınca onay penceresi çıkar; onaylanırsa kayıt silinir ve liste yenilenir.
- **Duplicate submit önleme:** Ekleme/güncelleme sonrası POST-redirect-GET kullanılarak formun tekrar gönderilmesi engellenir.

---

## Endpoint'ler ve Sayfalar

| HTTP | URL | Açıklama |
|------|-----|----------|
| GET | `/` | `index.html` üzerinden `/employees/list`e yönlendirir |
| GET | `/employees/list` | Çalışan listesi sayfası |
| GET | `/employees/showFormForAdd` | Yeni çalışan ekleme formu |
| POST | `/employees/save` | Çalışan kaydetme (ekleme veya güncelleme) |
| GET | `/employees/showFormForUpdate?employeeId={id}` | Güncelleme formu |
| GET | `/employees/delete?employeeId={id}` | Çalışan silme |

---

## Mimari

Uygulama **katmanlı mimari** kullanır:

1. **Controller (EmployeeController)**  
   İstekleri karşılar, servisi çağırır ve Thymeleaf view adını döner.

2. **Service (EmployeeService / EmployeeServiceImpl)**  
   İş kuralları ve işlem akışı; repository’yi kullanır.

3. **Repository (EmployeeRepository)**  
   Spring Data JPA ile veritabanı erişimi; `JpaRepository` ve özel metod `findAllByOrderByLastNameAsc()`.

4. **Entity (Employee)**  
   JPA entity: `id`, `firstName`, `lastName`, `email`; `employee` tablosu ile eşlenir.

Veri akışı: **Tarayıcı → Controller → Service → Repository → MySQL** ve cevap aynı katmanlardan geri döner; view’da Thymeleaf + Bootstrap kullanılır.

---

## Katkıda Bulunma

1. Projeyi fork edin.
2. Feature branch oluşturun: `git checkout -b feature/yeni-ozellik`
3. Değişikliklerinizi commit edin: `git commit -m 'Yeni özellik eklendi'`
4. Branch’i push edin: `git push origin feature/yeni-ozellik`
5. Pull request açın.

---

## Lisans

Bu proje eğitim / demo amaçlıdır. Kullanım koşulları proje sahibine aittir.

---

**Employee Directory** — Spring Boot 4 & Spring MVC CRUD Demo
