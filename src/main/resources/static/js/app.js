$(document).ready(function() {
    const API_URL = '/ktp';

    // Load initial data
    loadKtpData();

    // Form Submit Handler
    $('#ktpForm').on('submit', function(e) {
        e.preventDefault();

        const id = $('#ktpId').val();
        const data = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        if (id) {
            // Update
            $.ajax({
                url: `${API_URL}/${id}`,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    showNotification('Data berhasil diperbarui', 'success');
                    resetForm();
                    loadKtpData();
                },
                error: function(xhr) {
                    handleErrorResponse(xhr);
                }
            });
        } else {
            // Create
            $.ajax({
                url: API_URL,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    showNotification('Data berhasil ditambahkan', 'success');
                    resetForm();
                    loadKtpData();
                },
                error: function(xhr) {
                    handleErrorResponse(xhr);
                }
            });
        }
    });

    // Cancel Edit
    $('#btnCancel').on('click', function() {
        resetForm();
    });

    // Edit Button Handler
    $(document).on('click', '.edit-btn', function() {
        const id = $(this).data('id');

        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'GET',
            success: function(data) {
                $('#ktpId').val(data.id);
                $('#nomorKtp').val(data.nomorKtp);
                $('#namaLengkap').val(data.namaLengkap);
                $('#alamat').val(data.alamat);
                $('#tanggalLahir').val(data.tanggalLahir);
                $('#jenisKelamin').val(data.jenisKelamin);

                $('#formTitle').text('Edit Data KTP');
                $('#btnSubmit').text('Update');
                $('#btnCancel').show();
            },
            error: function() {
                showNotification('Gagal memuat data ketop', 'error');
            }
        });
    });

    // Delete Button Handler
    $(document).on('click', '.delete-btn', function() {
        if (confirm('Apakah Anda yakin ingin menghapus data ini?')) {
            const id = $(this).data('id');

            $.ajax({
                url: `${API_URL}/${id}`,
                type: 'DELETE',
                success: function() {
                    showNotification('Data berhasil dihapus', 'success');
                    loadKtpData();
                },
                error: function(xhr) {
                    handleErrorResponse(xhr);
                }
            });
        }
    });

    // Function to load data into table
    function loadKtpData() {
        $.ajax({
            url: API_URL,
            type: 'GET',
            success: function(data) {
                const tbody = $('#ktpTableBody');
                tbody.empty();

                if (data.length === 0) {
                    tbody.append('<tr><td colspan="6" style="text-align:center;">Tidak ada data KTP</td></tr>');
                    return;
                }

                data.forEach(function(ktp) {
                    const tr = `
                        <tr>
                            <td>${ktp.nomorKtp}</td>
                            <td>${ktp.namaLengkap}</td>
                            <td>${ktp.alamat}</td>
                            <td>${formatDate(ktp.tanggalLahir)}</td>
                            <td>${ktp.jenisKelamin}</td>
                            <td class="action-buttons">
                                <button class="btn btn-edit edit-btn" data-id="${ktp.id}">Edit</button>
                                <button class="btn btn-danger delete-btn" data-id="${ktp.id}">Hapus</button>
                            </td>
                        </tr>
                    `;
                    tbody.append(tr);
                });
            },
            error: function() {
                showNotification('Gagal memuat daftar KTP', 'error');
            }
        });
    }

    // Helpers
    function resetForm() {
        $('#ktpForm')[0].reset();
        $('#ktpId').val('');
        $('#formTitle').text('Tambah Data KTP');
        $('#btnSubmit').text('Simpan');
        $('#btnCancel').hide();
    }

    function showNotification(message, type) {
        const notif = $('#notification');
        notif.text(message)
             .removeClass('notif-success notif-error')
             .addClass(type === 'success' ? 'notif-success' : 'notif-error')
             .fadeIn();

        setTimeout(function() {
            notif.fadeOut();
        }, 5000);
    }

    function handleErrorResponse(xhr) {
        let errorMsg = 'Terjadi kesalahan sistem';
        if (xhr.responseJSON && xhr.responseJSON.error) {
            errorMsg = xhr.responseJSON.error;
        } else if (xhr.responseJSON && xhr.responseJSON.message) {
             errorMsg = xhr.responseJSON.message;
        }
        showNotification(errorMsg, 'error');
    }

    function formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString('id-ID', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    }
});
