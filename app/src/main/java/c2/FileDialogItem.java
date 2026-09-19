package c2;

import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;

public class FileDialogItem {
    public String name;
    public Long lastModified;
    public int size;
    public boolean isDirectory;
    public DocumentFile documentFile;
    public Uri uri;

    // Compatibility fields for decompiled references
    public String f3179a;
    public Long f3180b;
    public int f3181c;
    public boolean f3182d;
    public DocumentFile f3183e;
    public Uri f3184f;

    public FileDialogItem(String docId, String displayName, String mimeType, String flags, Long lastModified, int size, DocumentFile docFile, Uri uri) {
        this.name = displayName;
        this.lastModified = lastModified;
        this.size = size;
        this.documentFile = docFile;
        this.uri = uri;
        this.isDirectory = "vnd.android.document/directory".equals(mimeType);

        this.f3179a = this.name;
        this.f3180b = this.lastModified;
        this.f3181c = this.size;
        this.f3182d = this.isDirectory;
        this.f3183e = this.documentFile;
        this.f3184f = this.uri;
    }
}
