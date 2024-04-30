# TURİZM ACENTE SİSTEMİ PROJESİ

#### Java version 21 kullanılarak geliştirilmiştir.

### Proje Tanımı

- Bu proje, bir turizm acentesinin otel yönetimi, oda yönetimi ve rezervasyon işlemlerini gerçekleştirmek için geliştirilmiştir.
- Proje, Java, Swing, JDBC ve MySQL teknolojileri kullanılarak geliştirilmiştir.
- Proje, 2 tip kullanıcı (Admin ve Employee) ile çalışmaktadır.
- Proje, Admin kullanıcısı çalışanları yönetirken, Employee kullanıcısı otel yönetimi, oda yönetimi ve rezervasyon işlemlerini gerçekleştirir.
- Proje, Admin ve Employee kullanıcıları için ayrı ekranlar tasarlanmıştır.
- Proje, Admin ve Employee kullanıcıları için ayrı yetkilendirme işlemleri yapılmıştır.

### Proje Login ekranıyla başlar.

### LOGIN EKRANI

- Sistemde Admin ve Employee adında iki tip kullanıcı  tanımlanmıştır.

![Fotoğraf](/src/Images/GirişEkranı.png)


### ADMIN EKRANI

- Admin Ekranında çalışanları silme , ekleme ve filtreleme işlemleri yapılır.


![Fotoğraf](/src/Images/KullanıcıEkranı.png)

### Filtreleme işlemi

- Ad soyad , Kullanıcı Adı ve Üyelik Tipine göre Filtreleme Yapılabilir.

![Fotoğraf](/src/Images/Filtrelemeİslemi.png)

### Ekleme işlemi

- Ekleme işlemi yapıldıktan sonra tüm alanlar dolu ise  "İşlem Başarılı" mesajı verilir

![Fotoğraf](/src/Images/EklemeIslemi.png)

### Silme işlemi

- Silme işlemi yapıldıktan sonra "İşlem Başarılı" mesajı verilir

![Fotoğraf](/src/Images/SilmeIslemi.png)

### EMPLOYEE EKRANI

- Employee ekranında Otel yönetimi , Oda yönetimi ve rezervasyon ekranları bulunur.

![Fotoğraf](/src/Images/OtelYönetimi.png)

- Otel Yönetimi ekranında   sisteme yeni otel ekleme, otele özellik ekleme , otele oda ekleme
otel arama ve otel silme gibi işlemler yapılır .

### ODAYA ÖZELLİK EKLEME EKRANI

- Seçim yapmadan otele özellik eklenemez. Ekrana Hata mesajı verilir.
Bu ekranda pansiyon özellikleri , Tesis özellikleri ve Sezon ekleme işlemi yapılır.
Boş Alan bırakılırsa "Lütfen tüm alanları doldurun" mesajı verilir.
Ekleme işlemi yapıldıktan sonra tüm alanlar dolu ise  "İşlem Başarılı" mesajı verilir

![Fotoğraf](/src/Images/OdayaÖzellikEkleme.png)

### OTELE ODA EKLEME EKRANI

- Bu ekranda seçilen otele oda tipini,dönemi,pansiyon tipini stok ,ve ücretler belirlenerek oda eklenir.
Boş Alan bırakılırsa "Lütfen tüm alanları doldurun" mesajı verilir.
Ekleme işlemi yapıldıktan sonra tüm alanlar dolu ise  "İşlem Başarılı" mesajı verilir

![Fotoğraf](/src/Images/OdaEklemeEkranı.png)

### ODA YÖNETİMİ

- Oda Yönetimi ekranında Oda arama , Odaya Özellik ekleme , Oda silme ve Seçilen Odaya Rezervasyon yapma işlemleri yapılır.

![Fotoğraf](/src/Images/OdaYönetimi.png)

### REZERVASYON EKRANI

- Rezervasyon Ekranında sol bölümde seçilen odaya ait otel ve oda bilgileri yer alır. Sağ bölümde Müşteriye ait bilgiler yazılır.
Bu ekranda Rezerve edilen odanın Fiyat hesaplanması yapılır.Rezervasyon kaydedildikten sonra kullanıcıya İşlem Başarılı mesajı verilir.

![Fotoğraf](/src/Images/RezarvasyonEkrani.png)

### REZERVASYON LİSTESİ EKRANI

- Burada rezervasyonalar listelenir ve Rezervasyon silme işlemi yapılır.

![Fotoğraf](/src/Images/RezervasyonListesi.png)

#### Proje Geliştiricisi

- [Ali Osman UYSAL](https://www.linkedin.com/in/aliosmanuysal/)




