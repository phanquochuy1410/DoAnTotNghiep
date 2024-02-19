package com.example.demo.config;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class InHoaDon {

    public static void genHoaDon(String filePath, HoaDon hoaDon, List<HoaDonChiTiet> hoaDonChiTiets) throws IOException, DocumentException {

        Document document = new Document(PageSize.A5);
        Font f = new Font(BaseFont.createFont("C:\\Users\\huyxo\\Downloads\\vuArial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            LocalDate updateDate = hoaDon.getUpdate_at();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = updateDate.format(formatter);

            document.add(new Paragraph("                                           Hóa Đơn", f));
            document.add(new Paragraph("               ", f));
            document.add(new Paragraph("Mã đơn hàng: " + hoaDon.getMa() + "                              Ngày: " + formattedDate, f));
            document.add(new Paragraph("Tên khách hàng: " + (hoaDon.getTenNguoiNhan()), f));
            document.add(new Paragraph("---------------------------------------------------------------------------------------", f));
            for (HoaDonChiTiet hd : hoaDonChiTiets) {
                double gia = hd.getGia();
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String giaFormatted = currencyFormat.format(gia);

                double giaKhuyenMai = hd.getGiaSauKhuyenMai();
                NumberFormat currencyFormat1 = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String giaFormatted1 = currencyFormat1.format(giaKhuyenMai);

                double thanhTien = ((hd.getGia() * hd.getSoLuong()) - (hd.getGiaSauKhuyenMai() * hd.getSoLuong()));
                NumberFormat currencyFormat2 = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String giaFormatted2 = currencyFormat2.format(thanhTien);

                document.add(new Paragraph("Sản Phẩm :", f));
                document.add(new Paragraph(hd.getIdChiTietSanPham().getIdSanPham().getTen() + "(" + hd.getIdChiTietSanPham().getIdMauSac().getTen() + "," + hd.getIdChiTietSanPham().getIdKichCo().getTen() + ")", f));
                document.add(new Paragraph("Số Lượng "+"        "+    "Đơn giá  "+"        " + "Giá khuyến mại" +"              "+"Thành tiền", f));
                document.add(new Paragraph("    " + (hd.getSoLuong()) +"                 "+(giaFormatted)+"             " + (giaFormatted1) + "                  " +(giaFormatted2)   , f));
                document.add(new Paragraph("---------------------------------------------------------------------------------------", f));
            }

            double tongTien = hoaDon.getGia();
            NumberFormat currencyFormats = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String giaFormatteds = currencyFormats.format(tongTien);
            document.add(new Paragraph( "Tổng tiền "+"                                                                      "+(giaFormatteds), f));

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
