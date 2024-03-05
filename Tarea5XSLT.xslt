<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Tabla de artistas</title>
            </head>
            <body>
                <table>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Año de nacimiento</th>
                        <th>Año de fallecimiento</th>
                        <th>País</th>
                        <th>Página web</th>
                    </tr>
                     <xsl:apply-templates select="artistas/artista[nacimiento &gt; 1500]">
                        <xsl:sort select="nacimiento"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="artista">
        <tr>
            <td><xsl:value-of select="@cod"/></td>
            <td><xsl:value-of select="nombreCompleto"/></td>
            <td><xsl:value-of select="nacimiento"/></td>
            <td>
                <xsl:choose>
                    <xsl:when test="fallecimiento">
                        <xsl:value-of select="fallecimiento"/>
                    </xsl:when>
                    <xsl:otherwise>Desconocido</xsl:otherwise>
                </xsl:choose>
            </td>
            <td><xsl:value-of select="pais"/></td>
            <td><a href="{fichaCompleta}">Página web</a></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
