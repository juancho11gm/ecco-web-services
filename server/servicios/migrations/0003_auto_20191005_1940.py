# Generated by Django 2.1.7 on 2019-10-05 19:40

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('servicios', '0002_auto_20191005_1852'),
    ]

    operations = [
        migrations.RenameField(
            model_name='carritocompras',
            old_name='lista_servicios',
            new_name='servicios',
        ),
        migrations.RemoveField(
            model_name='carrito_servicio',
            name='cantidad',
        ),
    ]