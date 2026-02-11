package com.cibertec.santillan_project.service;

import com.cibertec.santillan_project.dto.CreateCotizacionRequest;
import com.cibertec.santillan_project.dto.CotizacionResponse;
import com.cibertec.santillan_project.entity.Cotizacion;
import com.cibertec.santillan_project.enums.CotizacionEstado;
import com.cibertec.santillan_project.enums.TipoProductoCotizacion;
import com.cibertec.santillan_project.repository.CotizacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CotizacionService {
    private final CotizacionRepository cotizacionRepo;

    public CotizacionService(CotizacionRepository cotizacionRepo) {
        this.cotizacionRepo = cotizacionRepo;
    }

    @Transactional
    public CotizacionResponse registrarCotizacion(CreateCotizacionRequest req) {
        Cotizacion cot = mapFromRequest(req);
        cot.setFecha(LocalDateTime.now());
        cot.setPrecioEstimado(calculateEstimatedPrice(req));
        cot.setEstado(CotizacionEstado.COTIZADA);
        Cotizacion saved = cotizacionRepo.save(cot);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<CotizacionResponse> listarCotizaciones() {
        return cotizacionRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public BigDecimal cotizar(CreateCotizacionRequest req) {
        return calculateEstimatedPrice(req);
    }


    private Cotizacion mapFromRequest(CreateCotizacionRequest req) {
        Cotizacion c = new Cotizacion();
        c.setTipoProducto(TipoProductoCotizacion.valueOf(req.getTipoProducto()));
        c.setMaterial(req.getMaterial());
        c.setKilataje(req.getKilataje());
        c.setPesoEstimado(req.getPesoEstimado());
        c.setTipoPiedra(req.getTipoPiedra());
        c.setQuilataje(req.getQuilataje());
        c.setCalidadPiedra(req.getCalidadPiedra());
        c.setCostoManoObra(req.getCostoManoObra());
        return c;
    }

    private CotizacionResponse mapToResponse(Cotizacion c) {
        CotizacionResponse r = new CotizacionResponse();
        r.setId(c.getId());
        r.setTipoProducto(c.getTipoProducto());
        r.setMaterial(c.getMaterial());
        r.setKilataje(c.getKilataje());
        r.setPesoEstimado(c.getPesoEstimado());
        r.setTipoPiedra(c.getTipoPiedra());
        r.setQuilataje(c.getQuilataje());
        r.setCalidadPiedra(c.getCalidadPiedra());
        r.setCostoManoObra(c.getCostoManoObra());
        r.setPrecioEstimado(c.getPrecioEstimado());
        r.setFecha(c.getFecha());
        r.setEstado(c.getEstado());
        return r;
    }


    private BigDecimal calculateEstimatedPrice(CreateCotizacionRequest req) {
        BigDecimal materialCost = calculateMaterialCost(req);
        BigDecimal stonesCost = calculateStonesCost(req);
        BigDecimal laborCost = req.getCostoManoObra() != null ? req.getCostoManoObra() : BigDecimal.ZERO;

        BigDecimal total = materialCost.add(stonesCost).add(laborCost).setScale(4, RoundingMode.HALF_UP);
        return total;
    }


    private BigDecimal calculateMaterialCost(CreateCotizacionRequest req) {
        BigDecimal pricePerGram = pricePerGramMaterial(req.getMaterial(), req.getKilataje());
        BigDecimal peso = req.getPesoEstimado() != null ? req.getPesoEstimado() : BigDecimal.ZERO;
        return peso.multiply(pricePerGram).setScale(4, RoundingMode.HALF_UP);
    }


    private BigDecimal pricePerGramMaterial(String material, Integer kilataje) {
        if (material == null) material = "";
        String m = material.trim().toLowerCase();

        BigDecimal base;
        if (m.contains("oro")) {

            base = BigDecimal.valueOf(70);
        } else if (m.contains("plata")) {
            base = BigDecimal.valueOf(40);
        } else if (m.contains("acero")) {
            base = BigDecimal.valueOf(25);
        } else {
            base = BigDecimal.valueOf(35);
        }

        int k = (kilataje != null && kilataje > 0) ? kilataje : 18;
        BigDecimal factor = new BigDecimal(k).divide(new BigDecimal(18), 4, RoundingMode.HALF_UP);
        return base.multiply(factor);
    }


    private BigDecimal calculateStonesCost(CreateCotizacionRequest req) {
        if (req.getQuilataje() == null) return BigDecimal.ZERO;

        BigDecimal carat = req.getQuilataje();

        String tipo = req.getTipoPiedra() != null ? req.getTipoPiedra().toLowerCase() : "";
        BigDecimal basePerCarat;

        switch (tipo) {
            case "diamante":
                basePerCarat = BigDecimal.valueOf(250);
                break;
            case "zafiro":
                basePerCarat = BigDecimal.valueOf(120);
                break;
            case "rubí":
                basePerCarat = BigDecimal.valueOf(180);
                break;
            default:
                basePerCarat = BigDecimal.valueOf(100);
        }

        String calidad = req.getCalidadPiedra() != null ? req.getCalidadPiedra().toUpperCase() : "";
        BigDecimal qualityFactor = BigDecimal.ONE;

        if ("AAA".equals(calidad)) {
            qualityFactor = BigDecimal.valueOf(1.30);
        } else if ("AA".equals(calidad)) {
            qualityFactor = BigDecimal.valueOf(1.15);
        }

        return carat.multiply(basePerCarat.multiply(qualityFactor))
                .setScale(4, RoundingMode.HALF_UP);
    }

    @Transactional
    public void eliminarCotizacion(Long id) {
        if (!cotizacionRepo.existsById(id)) {
            throw new RuntimeException("Cotización no encontrada");
        }
        cotizacionRepo.deleteById(id);
    }

}