package com.camping101.beta.db.entity.site;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiteFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_file_id")
    private AttachFile attachFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;

    public SiteFile(AttachFile attachFile, String name) {
        this.attachFile = attachFile;
        this.name = name;
    }

    public static SiteFile create(AttachFile attachFile, String name) {
        SiteFile siteFile = new SiteFile();
        siteFile.attachFile = attachFile;
        siteFile.name = name;
        return siteFile;
    }

    public void addSite(Site site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SiteFile siteFile = (SiteFile) o;
        return Objects.equals(getName(), siteFile.getName()) && Objects.equals(
            getAttachFile(), siteFile.getAttachFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAttachFile());
    }
}
