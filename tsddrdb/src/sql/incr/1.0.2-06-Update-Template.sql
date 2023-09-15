UPDATE tsddr_t_report SET xml_report = '<?xml version="1.0" encoding="UTF-8"?>
<!-- Created with Jaspersoft Studio version 7.9.0.final utlizzo versione della libreria JasperReports6.16.0-48579d909b7943b64690c65c71e07e0b80981928  -->
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="UMA_MODELLO_26_V" pageWidth="595" pageHeight="1100" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" whenResourceMissingType="Error" uuid="3e2d4fe0-be96-423a-93be-f74048f83696">
	<property name="com.jaspersoft.studio.data.defaultdataadapter" value="Nuovo Data Adapter (2)"/>
	<subDataset name="SezioneQuattroDataset" uuid="9736c357-acd4-42c1-841b-e8ed511a1aeb">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="percScarto" class="java.math.BigDecimal"/>
		<field name="obiettSca" class="java.lang.String"/>
		<field name="obiettRec" class="java.lang.String"/>
		<field name="percRecupero" class="java.math.BigDecimal"/>
		<field name="descLinea" class="java.lang.String"/>
		<field name="descSommaria" class="java.lang.String"/>
		<field name="sezPimp1" class="java.lang.String"/>
		<field name="pimpDes" class="java.lang.String"/>
		<field name="sezPimp2" class="java.lang.String"/>
		<field name="obiett" class="java.lang.String"/>
		<field name="descSezione5" class="java.lang.String"/>
		<field name="descSezione4" class="java.lang.String"/>
		<field name="descSezione3" class="java.lang.String"/>
		<field name="descSezione2" class="java.lang.String"/>
		<field name="descSezione1" class="java.lang.String"/>
		<field name="riiTotDesc" class="java.lang.String"/>
		<field name="matTotDesc" class="java.lang.String"/>
		<field name="totMat" class="java.math.BigDecimal"/>
		<field name="totRu" class="java.math.BigDecimal"/>
		<field name="totRru" class="java.math.BigDecimal"/>
		<field name="totRii" class="java.math.BigDecimal"/>
		<field name="ruTotDesc" class="java.lang.String"/>
		<field name="rruTotDesc" class="java.lang.String"/>
		<field name="riiTesta0" class="java.lang.String"/>
		<field name="riiTesta1" class="java.lang.String"/>
		<field name="riiTesta2" class="java.lang.String"/>
		<field name="riiTesta3" class="java.lang.String"/>
		<field name="matTesta0" class="java.lang.String"/>
		<field name="matTesta2" class="java.lang.String"/>
		<field name="matTesta1" class="java.lang.String"/>
		<field name="rruTesta0" class="java.lang.String"/>
		<field name="rruTesta1" class="java.lang.String"/>
		<field name="rruTesta2" class="java.lang.String"/>
		<field name="rruTesta3" class="java.lang.String"/>
		<field name="rruTesta4" class="java.lang.String"/>
		<field name="ruTesta0" class="java.lang.String"/>
		<field name="ruTesta1" class="java.lang.String"/>
		<field name="ruTesta2" class="java.lang.String"/>
		<field name="ruTesta3" class="java.lang.String"/>
		<field name="ruTesta4" class="java.lang.String"/>
		<field name="sezioneRii" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
		<field name="sezioneMat" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
		<field name="sezioneRu" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
		<field name="sezioneRru" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	</subDataset>
	<subDataset name="RiiDataset" uuid="955dcc39-d00d-4361-81ec-09298819eeb4">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="quantita" class="java.math.BigDecimal"/>
		<field name="eer.descrizione" class="java.lang.String"/>
		<field name="eer.codiceEer" class="java.lang.String"/>
		<field name="idPrevConsDett" class="java.lang.Long"/>
		<field name="descMatUscita" class="java.lang.String"/>
		<field name="destinazione" class="java.lang.String"/>
	</subDataset>
	<subDataset name="MatDataset" uuid="9685d9f4-365d-44f6-ad5f-eb32119d4583">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="quantita" class="java.math.BigDecimal"/>
		<field name="eer.descrizione" class="java.lang.String"/>
		<field name="eer.codiceEer" class="java.lang.String"/>
		<field name="descMatUscita" class="java.lang.String"/>
		<field name="idPrevConsDett" class="java.lang.Long"/>
		<field name="destinazione" class="java.lang.String"/>
	</subDataset>
	<subDataset name="RruDataset" uuid="949c45dc-cb41-42de-9da8-1c56030da298">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="quantita" class="java.math.BigDecimal"/>
		<field name="eer.descrizione" class="java.lang.String"/>
		<field name="eer.codiceEer" class="java.lang.String"/>
		<field name="descMatUscita" class="java.lang.String"/>
		<field name="idPrevConsDett" class="java.lang.Long"/>
		<field name="destinazione" class="java.lang.String"/>
	</subDataset>
	<subDataset name="RuDataset" uuid="0bc4e1ad-8ac7-4e95-92fa-43d575e6e411">
		<queryString>
			<![CDATA[]]>
		</queryString>
		<field name="quantita" class="java.math.BigDecimal"/>
		<field name="eer.descrizione" class="java.lang.String"/>
		<field name="eer.codiceEer" class="java.lang.String"/>
		<field name="descMatUscita" class="java.lang.String"/>
		<field name="idPrevConsDett" class="java.lang.Long"/>
		<field name="destinazione" class="java.lang.String"/>
	</subDataset>
	<parameter name="LOGO_RP" class="java.awt.image.BufferedImage">
		<parameterDescription><![CDATA[]]></parameterDescription>
	</parameter>
	<parameter name="H_S_1" class="java.lang.String"/>
	<parameter name="H_S_2" class="java.lang.String"/>
	<parameter name="H_S_3" class="java.lang.String"/>
	<parameter name="H_S_4" class="java.lang.String"/>
	<parameter name="H_C_1" class="java.lang.String"/>
	<parameter name="H_C_2" class="java.lang.String"/>
	<parameter name="H_C_3" class="java.lang.String"/>
	<parameter name="TITOLO" class="java.lang.String"/>
	<parameter name="H_D_1" class="java.lang.String"/>
	<parameter name="H_D_2" class="java.lang.String"/>
	<parameter name="ANNO" class="java.lang.Long"/>
	<parameter name="CF_GESTORE" class="java.lang.String"/>
	<parameter name="RAGSOG_GESTORE" class="java.lang.String"/>
	<parameter name="NATGIU_GESTORE" class="java.lang.String"/>
	<parameter name="SEDIME_GESTORE" class="java.lang.String"/>
	<parameter name="IND_GESTORE" class="java.lang.String"/>
	<parameter name="COM_GESTORE" class="java.lang.String"/>
	<parameter name="CAP_GESTORE" class="java.lang.String"/>
	<parameter name="PROV_GESTORE" class="java.lang.String"/>
	<parameter name="TEL_GESTORE" class="java.lang.String"/>
	<parameter name="MAIL_GESTORE" class="java.lang.String"/>
	<parameter name="PEC_GESTORE" class="java.lang.String"/>
	<parameter name="SEZ_L_R" class="java.lang.String"/>
	<parameter name="CF" class="java.lang.String"/>
	<parameter name="NOME" class="java.lang.String"/>
	<parameter name="COGNOME" class="java.lang.String"/>
	<parameter name="QUALIFICA" class="java.lang.String"/>
	<parameter name="SEZ_DICH" class="java.lang.String"/>
	<parameter name="SEZ_CONF" class="java.lang.String"/>
	<parameter name="totaleQuantita" class="java.lang.Double"/>
	<parameter name="totaleImporto" class="java.lang.Double"/>
	<parameter name="SEZ_IMP" class="java.lang.String"/>
	<parameter name="NOME_IMPIANTO" class="java.lang.String"/>
	<parameter name="TIPO_IMPIANTO" class="java.lang.String"/>
	<parameter name="STATO_IMPIANTO" class="java.lang.String"/>
	<parameter name="SEDIME_IMPIANTO" class="java.lang.String"/>
	<parameter name="INDIRIZZO_IMPIANTO" class="java.lang.String"/>
	<parameter name="COMUNE_IMPIANTO" class="java.lang.String"/>
	<parameter name="CAP_IMPIANTO" class="java.lang.String"/>
	<parameter name="PROV_IMPIANTO" class="java.lang.String"/>
	<parameter name="SEZ_PIE_1" class="java.lang.String"/>
	<parameter name="INFO_T" class="java.lang.String"/>
	<parameter name="INFO_D" class="java.lang.String"/>
	<parameter name="SEZ_ANN" class="java.lang.String"/>
	<parameter name="SEZ_VERS_MR" class="java.lang.String"/>
	<parameter name="SEZ_PIMP1" class="java.lang.String"/>
	<parameter name="SEZ_PIE" class="java.lang.String"/>
	<parameter name="MRR-1" class="java.lang.String"/>
	<parameter name="SEZ_R_MR" class="java.lang.String"/>
	<parameter name="MRR-3" class="java.lang.String"/>
	<parameter name="MRR-2" class="java.lang.String"/>
	<parameter name="MODALITA" class="java.lang.String"/>
	<parameter name="RMR_ANNO_TRIBUTO" class="java.lang.Long"/>
	<parameter name="DATA_INVIO_DOC" class="java.util.Date"/>
	<parameter name="RMR_NUM_PROTOCOLLO" class="java.lang.String"/>
	<parameter name="RMR_DATA_PROTOCOLLO" class="java.util.Date"/>
	<parameter name="SEZ_PIE_3" class="java.lang.String"/>
	<parameter name="DATA_DOCUMENTO" class="java.util.Date"/>
	<parameter name="sezioneQuattro" class="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"/>
	<queryString language="JSON">
		<![CDATA[modello26]]>
	</queryString>
	<title>
		<band height="1020" splitType="Stretch">
			<property name="com.jaspersoft.studio.layout" value="com.jaspersoft.studio.editor.layout.FreeLayout"/>
			<textField textAdjust="ScaleFont">
				<reportElement x="6" y="35" width="156" height="12" uuid="31c7ee68-36d2-4d5d-8709-8d06620e95cd">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_1}]]></textFieldExpression>
			</textField>
			<image onErrorType="Blank">
				<reportElement x="50" y="0" width="90" height="35" uuid="47de5b3f-19b4-41fb-b2c4-3d2bc39fb0dd"/>
				<imageExpression><![CDATA[$P{LOGO_RP}]]></imageExpression>
			</image>
			<staticText>
				<reportElement mode="Opaque" x="0" y="80" width="555" height="3" backcolor="#374D4F" uuid="22581b8b-75e9-4ab1-9eaa-1409f1a753aa">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.width" value="pixel"/>
				</reportElement>
				<box padding="1"/>
				<textElement textAlignment="Center">
					<font size="14" isBold="false"/>
				</textElement>
				<text><![CDATA[]]></text>
			</staticText>
			<textField>
				<reportElement x="6" y="44" width="156" height="11" uuid="c598dd7f-1181-4f7e-ba85-f837a85b9d95">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_2}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="6" y="53" width="156" height="11" uuid="ac84e615-e24e-4c17-aeaf-05e3ec297088">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_3}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="6" y="62" width="156" height="9" uuid="0e51fd7d-f78f-470a-a29c-a1ef14da974f">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="7" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_S_4}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement x="170" y="0" width="210" height="34" uuid="b417a835-96fb-4f27-9438-594ff717a10d">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="20" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{TITOLO}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="170" y="34" width="210" height="19" uuid="b07ca9f8-9b3c-49d3-8014-d6a3a045be4f">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_C_1}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement x="170" y="53" width="210" height="11" uuid="d9756cc6-9881-461b-9ba8-90f4f2c3e5e1">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="7" isItalic="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_C_2}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="170" y="62" width="208" height="9" uuid="2f6fa418-fe0c-49b7-b8f7-5a2f28d9a691">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="7" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_C_3}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="400" y="10" width="140" height="12" uuid="b14c77a2-7b54-455e-a543-cdf017bdc408">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_D_1}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="400" y="22" width="140" height="29" uuid="4dc5acc6-4d20-4c97-9e64-a578bf64c716">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="18" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{ANNO}]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="400" y="51" width="140" height="20" uuid="2ed34758-e20d-4f8b-9796-573293e17d1b">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
					<property name="com.jaspersoft.studio.unit.y" value="px"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Top">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{H_D_2}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement positionType="Float" x="4" y="909" width="26" height="10" backcolor="#FFFFFF" uuid="8be1c054-3684-4186-96e4-c62297effec2">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Data]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy">
				<reportElement key="" positionType="Float" x="31" y="909" width="117" height="10" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{DATA_DOCUMENTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="5" y="927" width="544" height="10" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_PIE_1}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement positionType="Float" x="0" y="940" width="555" height="2" backcolor="#000000" uuid="b1b28746-ca42-4cbc-9886-d6ec846a2cb8">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="180" y="943" width="200" height="10" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Top">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{INFO_T}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="4" y="953" width="544" height="34" backcolor="#E0E0E0" uuid="758605f9-3fdd-4a67-9ec7-d7f1ea30ea90">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{INFO_D}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement positionType="Float" x="0" y="900" width="555" height="2" backcolor="#000000" uuid="2d4f6fa3-1319-46e5-b7f7-6141c3a673b8">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="5" y="83" width="544" height="15" uuid="fa31701c-6bfa-46e5-a1b4-188961ab990c"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Bottom">
					<font size="10" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_DICH}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="100" width="150" height="8" backcolor="#FFFFFF" uuid="8417e2af-d58e-4778-bea2-218d5e78428c"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Codice Fiscale / Partita IVA]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="109" width="150" height="16" backcolor="#E0E0E0" uuid="047a9e29-5e25-46b9-b6ee-6526b12d46b1"/>
				<box topPadding="0" leftPadding="5" bottomPadding="0" rightPadding="0">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CF_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="162" y="100" width="228" height="8" backcolor="#FFFFFF" uuid="c718563b-3c7f-49c6-8d22-2c55d7b4908b"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Denominazione o Ragione Sociale]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="162" y="109" width="228" height="16" backcolor="#E0E0E0" uuid="865dc9c2-f26b-49fe-9a3a-6e93c1314325"/>
				<box topPadding="0" leftPadding="5" bottomPadding="0" rightPadding="0">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{RAGSOG_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="400" y="100" width="150" height="8" backcolor="#FFFFFF" uuid="716534c4-b0bb-4df1-8d23-fc62bd9cc2d3"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Natura Giuridica]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="400" y="109" width="150" height="16" backcolor="#E0E0E0" uuid="5915093a-5637-4ab1-bc59-211ff3f4f49f"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{NATGIU_GESTORE}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="137" width="65" height="16" backcolor="#E0E0E0" uuid="e3d31ceb-4e94-46b9-9769-3cf84d07c4ae"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEDIME_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="128" width="65" height="8" backcolor="#FFFFFF" uuid="88f5d5cf-0380-4022-841f-fa487b40c759"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Sedime]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="77" y="137" width="210" height="16" backcolor="#E0E0E0" uuid="07b10a58-11eb-4a5b-a13e-a699d6c336dd"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{IND_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="77" y="128" width="210" height="8" backcolor="#FFFFFF" uuid="80577b62-1774-4030-8e2e-44778f07fb11"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Indirizzo]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="294" y="137" width="106" height="16" backcolor="#E0E0E0" uuid="4589662e-e119-4897-8975-f36f732e555c"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{COM_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="294" y="128" width="106" height="8" backcolor="#FFFFFF" uuid="38562691-4d4e-4db1-b434-1d70d723a51c"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Comune di domicilio Fiscale]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="405" y="137" width="50" height="16" backcolor="#E0E0E0" uuid="e00f9b56-73cb-4dd2-8206-24f870657847"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CAP_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="405" y="128" width="50" height="8" backcolor="#FFFFFF" uuid="c25fec73-40f7-410e-9167-9ebf0153b98b"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[CAP]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="462" y="137" width="88" height="16" backcolor="#E0E0E0" uuid="d5c56673-4042-4dc9-89fb-332e97c4fa2f"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{PROV_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="462" y="128" width="88" height="8" backcolor="#FFFFFF" uuid="e45b207b-013e-4c77-b78b-45cdc83ff4fb"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Provincia/CM]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="166" width="126" height="16" backcolor="#E0E0E0" uuid="805d1592-0298-4c42-940a-928df5bb6d72"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{TEL_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="157" width="126" height="8" backcolor="#FFFFFF" uuid="748db9bf-92c5-461c-8a2a-6c0fafe98c94"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Numero di Telefono]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="141" y="166" width="200" height="16" backcolor="#E0E0E0" uuid="d7a3835e-5515-4938-af2e-b3fc42caeebb"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{MAIL_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="141" y="157" width="200" height="8" backcolor="#FFFFFF" uuid="2363dc81-e46a-4444-8aed-31d56e94e57c"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Indirizzo di posta elettronica]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="346" y="166" width="204" height="16" backcolor="#E0E0E0" uuid="9a4c54cc-53b9-404e-83c9-dc25278e80f1"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{PEC_GESTORE}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="346" y="157" width="204" height="8" backcolor="#FFFFFF" uuid="5ebfe27b-de30-4b34-922e-9642d89ca0b2"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Posta Elettronica Certificata]]></text>
			</staticText>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="5" y="187" width="545" height="15" uuid="05cb2164-f51b-4556-af1e-09db5eb3d28c"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement verticalAlignment="Bottom">
					<font size="10" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_L_R}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="205" width="150" height="8" backcolor="#FFFFFF" uuid="37182578-361a-4e3a-901b-4a2324c7cccc"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Codice Fiscale / Partita IVA]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="214" width="150" height="16" backcolor="#E0E0E0" uuid="6648010a-e01d-4cf2-925b-3bb3f251556f"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CF}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="162" y="214" width="125" height="16" backcolor="#E0E0E0" uuid="b193c1c2-bb31-4710-920b-676ac1f977fa"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{NOME}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="162" y="205" width="125" height="8" backcolor="#FFFFFF" uuid="4a7076b5-32eb-414b-bed9-62d258ac25da"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Nome]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="294" y="214" width="125" height="16" backcolor="#E0E0E0" uuid="658452fc-6f83-4ca4-947f-7db389526d9d"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{COGNOME}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="294" y="205" width="125" height="8" backcolor="#FFFFFF" uuid="fa5249bd-af82-4055-85b1-18e8bf593372"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Cognome]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="425" y="214" width="125" height="16" backcolor="#E0E0E0" uuid="d1625634-294c-4f1b-bea1-1beac9af23b9"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{QUALIFICA}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="425" y="205" width="125" height="8" backcolor="#FFFFFF" uuid="5c1cd6dc-5d1c-4d90-b5e1-d349d93cc458"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Qualifica]]></text>
			</staticText>
			<rectangle>
				<reportElement x="0" y="240" width="555" height="2" backcolor="#000000" uuid="29793ebc-973c-49d9-99f4-2b8fd6d1c8ec">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="6" y="243" width="544" height="15" uuid="24c258a6-4308-4418-8e48-0e6a2fdb3234"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_IMP}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="260" width="246" height="8" backcolor="#FFFFFF" uuid="d26ddde0-e1bd-4cb8-acb8-fbc73fac3f69"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Denominazione impianto]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="269" width="246" height="16" backcolor="#E0E0E0" uuid="c7757233-cb04-422a-822c-7f4e3af32dba"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{NOME_IMPIANTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="261" y="269" width="129" height="16" backcolor="#E0E0E0" uuid="9f8ebe2d-1003-4bd0-a0ea-dbd9507cc2bf"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{TIPO_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="261" y="260" width="129" height="8" backcolor="#FFFFFF" uuid="3fee3bd0-cb83-4c85-953c-d31f5f09d6db"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Tipo impianto]]></text>
			</staticText>
			<staticText>
				<reportElement x="402" y="260" width="148" height="8" backcolor="#FFFFFF" uuid="c98ad5f5-762e-4883-a67b-fceca4debe94"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Stato impianto]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="402" y="269" width="148" height="16" backcolor="#E0E0E0" uuid="d0ab7ff4-6a3f-462f-b927-ecf3d561c11f"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{STATO_IMPIANTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="5" y="298" width="65" height="16" backcolor="#E0E0E0" uuid="93629fe8-67e3-4858-98e0-f495982b6cd5"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEDIME_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="5" y="289" width="65" height="8" backcolor="#FFFFFF" uuid="1873e771-b327-42ec-a928-868fc5573130"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Sedime]]></text>
			</staticText>
			<staticText>
				<reportElement x="77" y="289" width="210" height="8" backcolor="#FFFFFF" uuid="4b4d45b2-134f-48dd-ab2f-3f9f9de08f51"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Indirizzo]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="77" y="298" width="210" height="16" backcolor="#E0E0E0" uuid="e50dfed9-9e75-4fbf-9c90-de4515a3006f"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{INDIRIZZO_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="294" y="289" width="106" height="8" backcolor="#FFFFFF" uuid="22229c20-da64-4f5b-83fa-38fcd2fc55a2"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Comune di ubicazione]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="294" y="298" width="106" height="16" backcolor="#E0E0E0" uuid="cb204da3-6768-428b-9126-9174df025e0e"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{COMUNE_IMPIANTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="405" y="298" width="50" height="16" backcolor="#E0E0E0" uuid="76d9bad9-6b7c-4988-8617-d4bb4e3b7cad"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{CAP_IMPIANTO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="405" y="289" width="50" height="8" backcolor="#FFFFFF" uuid="8feb702c-0b03-4ab5-88e2-4516988c4fd9"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[CAP]]></text>
			</staticText>
			<staticText>
				<reportElement x="462" y="289" width="88" height="8" backcolor="#FFFFFF" uuid="68dc019e-e5c2-4a9c-98cc-09c3d1ac2219"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Provincia/CM]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="462" y="298" width="88" height="16" backcolor="#E0E0E0" uuid="fb324b9c-cdb0-4be4-872d-628a7aa76e33"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{PROV_IMPIANTO}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement x="0" y="324" width="555" height="2" backcolor="#000000" uuid="abd4424d-7991-4a9d-9888-b6479ac58b7a">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="6" y="326" width="544" height="15" uuid="9e1e5aee-b3e5-43f3-8a09-52d5094acf53"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_R_MR}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="6" y="349" width="104" height="21" uuid="3f09637d-8618-4855-a61f-611b7d2aa512"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{MRR-1}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="119" y="349" width="68" height="21" uuid="48871840-2b93-4673-9cb5-df9399e6230b"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="false"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{RMR_ANNO_TRIBUTO}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="261" y="349" width="80" height="21" uuid="16d35f9f-74b8-458f-8803-3553728a654a"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="false"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{MRR-2}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy" isBlankWhenNull="true">
				<reportElement key="" x="348" y="354" width="92" height="16" backcolor="#E0E0E0" uuid="5d06c48c-6e35-4fae-b341-74fba444f1e5"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{RMR_DATA_PROTOCOLLO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="348" y="345" width="92" height="8" backcolor="#FFFFFF" uuid="a67ebbb3-5146-411b-a010-bf22801007ed"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Data]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="440" y="354" width="92" height="16" backcolor="#E0E0E0" uuid="214199aa-59ea-4cc6-8fff-32bc435819f9"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{RMR_NUM_PROTOCOLLO}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="440" y="345" width="92" height="8" backcolor="#FFFFFF" uuid="b66b6446-7f24-45fa-b57d-0cb837a1bdf5"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Protocollo]]></text>
			</staticText>
			<textField textAdjust="ScaleFont">
				<reportElement key="" x="6" y="381" width="526" height="19" uuid="96704ee7-afd6-4452-ae02-b8e5de1c7178"/>
				<textElement textAlignment="Left" verticalAlignment="Bottom">
					<font isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{MRR-3}]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" pattern="dd/MM/yyyy" isBlankWhenNull="true">
				<reportElement key="" x="6" y="414" width="92" height="16" backcolor="#E0E0E0" uuid="c37189e1-6c56-4346-bb90-847d4e8a5892">
					<property name="com.jaspersoft.studio.unit.x" value="pixel"/>
				</reportElement>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{DATA_INVIO_DOC}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="6" y="405" width="92" height="8" backcolor="#FFFFFF" uuid="259d9593-1c9e-4587-a993-13f9469e6baf"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Data]]></text>
			</staticText>
			<textField textAdjust="ScaleFont" isBlankWhenNull="true">
				<reportElement key="" x="110" y="414" width="422" height="16" backcolor="#E0E0E0" uuid="141c0123-391a-495a-8c96-cf8a1bfe374a"/>
				<box leftPadding="5">
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="10"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{MODALITA}]]></textFieldExpression>
			</textField>
			<staticText>
				<reportElement x="110" y="405" width="422" height="8" backcolor="#FFFFFF" uuid="b40d21fe-5b8e-49c7-a7b3-9ade8c01aec9"/>
				<box>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement>
					<font size="6"/>
				</textElement>
				<text><![CDATA[Modalita''(PEC, posta elettronica ordinaria, posta raccomandata, posta ordinaria) - indicare sempre l''indirizzo]]></text>
			</staticText>
			<rectangle>
				<reportElement x="0" y="441" width="555" height="2" backcolor="#000000" uuid="80abd9db-22f7-4039-aae2-aa6327dc97de">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<textField textAdjust="ScaleFont">
				<reportElement key="" positionType="Float" x="4" y="995" width="544" height="10" backcolor="#E0E0E0" uuid="f2a651c6-9ade-4c05-a252-4384d1b12905">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="6" isBold="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{SEZ_PIE_3}]]></textFieldExpression>
			</textField>
			<rectangle>
				<reportElement positionType="Float" x="0" y="921" width="555" height="2" backcolor="#000000" uuid="51a2d673-943c-44aa-89e0-eba1410a6361">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<rectangle>
				<reportElement positionType="Float" x="0" y="990" width="555" height="2" backcolor="#000000" uuid="802479e6-d4ee-45b5-942e-24986838d035">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
			</rectangle>
			<componentElement>
				<reportElement stretchType="ElementGroupHeight" x="0" y="447" width="540" height="449" uuid="8407da46-5db2-4029-b22a-935312f47e75"/>
				<jr:list xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd" printOrder="Vertical">
					<datasetRun subDataset="SezioneQuattroDataset" uuid="27427a9d-f157-49c7-a5ee-2857920ee561">
						<dataSourceExpression><![CDATA[$P{sezioneQuattro}]]></dataSourceExpression>
					</datasetRun>
					<jr:listContents height="449" width="540">
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="2" width="534" height="15" uuid="026afc59-419a-40bc-98ea-db01530f0b03"/>
							<textElement verticalAlignment="Bottom">
								<font size="10" isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{sezPimp1}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="21" width="534" height="28" backcolor="#E0E0E0" uuid="a5128ccf-7fd0-4b06-851a-a47846fc8e8d">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<textElement textAlignment="Left" verticalAlignment="Middle">
								<font size="6" isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{sezPimp2}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" x="6" y="56" width="100" height="14" uuid="f94e52cb-21c4-40ce-b054-fdd96c3af725"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{pimpDes}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" x="112" y="56" width="428" height="14" uuid="0c7e81bb-6d32-4d6d-a865-fa25846225c3"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{descLinea}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" x="6" y="74" width="100" height="14" uuid="31078875-3107-4e96-9c03-c90a88059b6b"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{descSezione1}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" isBlankWhenNull="true">
							<reportElement key="" positionType="Float" x="6" y="93" width="534" height="46" backcolor="#E0E0E0" uuid="9aa7afd3-6332-450c-974f-78c5317c91d1">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.y" value="px"/>
							</reportElement>
							<textElement textAlignment="Left" verticalAlignment="Middle">
								<font size="6" isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{descSommaria}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="144" width="524" height="14" uuid="ae14daea-8c7b-4a60-9901-f436157e92d0"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{descSezione2}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="410" width="194" height="14" uuid="b348ef60-5986-417f-b1aa-bf0e590b9450"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{obiett}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="428" width="154" height="14" uuid="f5dd3bae-a00e-47a5-aa3f-f5f1380fabd1"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{obiettRec}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.##;#,##0.##-">
							<reportElement key="" positionType="Float" x="170" y="428" width="40" height="14" uuid="e5035760-951f-47db-af8a-3e4268fe8669"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{percRecupero}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="250" y="428" width="150" height="14" uuid="48b34d20-819c-4c94-8319-eb7c5bb68ed3"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{obiettSca}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.##;#,##0.##-">
							<reportElement key="" positionType="Float" x="410" y="428" width="40" height="14" uuid="3664bca7-98f2-4b5e-83c9-b5c057749530"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{percScarto}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="392" y="186" width="69" height="14" uuid="3b6670cb-9c45-4d75-a094-eb5cdd737d51"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{riiTotDesc}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" positionType="Float" x="461" y="186" width="69" height="14" uuid="cf813a3f-2db2-43bb-814b-e46acd896cf7"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totRii}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" positionType="Float" x="461" y="252" width="69" height="14" uuid="e89ff172-d040-491a-b280-360c818e7c3d"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totMat}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="392" y="252" width="69" height="14" uuid="8644c175-b696-4311-84b3-c0ddc344bdb8"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{matTotDesc}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="210" width="524" height="14" uuid="f370ce85-ed9e-49f8-9cce-50dc882e3f6b"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{descSezione3}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" positionType="Float" x="461" y="316" width="69" height="14" uuid="ab60e9bd-caf5-4427-a71c-edab18f70079"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totRru}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="392" y="316" width="69" height="14" uuid="4806930f-ab35-4ca6-87e8-3cea2c66c07d"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rruTotDesc}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="274" width="524" height="14" uuid="bdc0c47a-4d05-48ae-8c1b-5ae52f050e6d"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{descSezione4}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="392" y="382" width="69" height="14" uuid="ec43b89d-419c-4b0b-8fc2-fb078b087f7e"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{ruTotDesc}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="340" width="524" height="14" uuid="682eafcf-e1b6-403a-a9bb-dff2a5576992"/>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{descSezione5}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-">
							<reportElement key="" positionType="Float" x="461" y="382" width="69" height="14" uuid="56da37cb-d8fb-43d0-bf8e-37c55b3e7043"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="false"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{totRu}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="158" width="60" height="14" uuid="d537a7a6-b657-43b6-9121-f8f2cb7cd08d"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{riiTesta0}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="66" y="158" width="184" height="14" uuid="b49b43a5-c850-4731-b5d5-dbdcf7d58678"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{riiTesta1}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="250" y="158" width="234" height="14" uuid="90f1cd1f-e39a-46f9-9f68-20f0cd58196a"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{riiTesta2}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="484" y="158" width="46" height="14" uuid="be7aad62-6a58-44d3-8359-6915b20520f4"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{riiTesta3}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="484" y="224" width="46" height="14" uuid="08453565-11e0-49a3-8a35-d28076d3270c"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{matTesta2}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="224" width="60" height="14" uuid="232dce71-65a1-470c-828a-89283e779226"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{matTesta0}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="66" y="224" width="418" height="14" uuid="58da01b9-3800-4375-a4e8-5869dc469554"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{matTesta1}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="160" y="288" width="173" height="14" uuid="3962e6fa-bf48-49f8-8918-3d954549fb05"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rruTesta2}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="288" width="60" height="14" uuid="39517077-ac27-4afa-bb91-903f76a484d8"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rruTesta0}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="66" y="288" width="94" height="14" uuid="475cf2a8-71a2-4f28-8a10-bb24396e84df"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rruTesta1}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="333" y="288" width="150" height="14" uuid="43136953-243b-4a31-a190-f37641a03c73"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rruTesta3}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="483" y="288" width="47" height="14" uuid="b6f741d5-6a1d-4e95-abfd-0591e4ec24b8">
								<property name="com.jaspersoft.studio.unit.x" value="px"/>
							</reportElement>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{rruTesta4}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="483" y="354" width="47" height="14" uuid="f336e91f-8584-48ba-bd01-5fbc67efe413"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{ruTesta4}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="333" y="354" width="150" height="14" uuid="4bfe4675-4763-4a94-97da-2218ea18cb20">
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
							</reportElement>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{ruTesta3}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="66" y="354" width="94" height="14" uuid="431abdc2-e744-4680-9ac8-8765873f8532"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{ruTesta1}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="160" y="354" width="173" height="14" uuid="7552d92d-0d3a-4b77-8982-7ed7d6b324c6"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{ruTesta2}]]></textFieldExpression>
						</textField>
						<textField textAdjust="ScaleFont">
							<reportElement key="" positionType="Float" x="6" y="354" width="60" height="14" uuid="d5454ba0-f1ef-4097-941c-e8aecfce5ca9"/>
							<box>
								<pen lineWidth="0.25"/>
							</box>
							<textElement textAlignment="Left" verticalAlignment="Bottom">
								<font isBold="true"/>
							</textElement>
							<textFieldExpression><![CDATA[$F{ruTesta0}]]></textFieldExpression>
						</textField>
						<componentElement>
							<reportElement positionType="Float" x="6" y="172" width="524" height="14" uuid="08be803d-e71e-4dcf-b435-3a1e2457287f">
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
							</reportElement>
							<jr:list printOrder="Vertical">
								<datasetRun subDataset="RiiDataset" uuid="a14995b1-2499-4754-8574-071d2e0c5e3a">
									<dataSourceExpression><![CDATA[$F{sezioneRii}]]></dataSourceExpression>
								</datasetRun>
								<jr:listContents height="14" width="524">
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="0" y="0" width="60" height="14" uuid="911ecc66-2b74-46af-8dcc-cb25d563f8ac">
											<property name="com.jaspersoft.studio.unit.width" value="px"/>
										</reportElement>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{idPrevConsDett}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="60" y="0" width="184" height="14" uuid="a44d8cc3-40a5-46b1-b6b8-680bf1bed51e"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{eer.codiceEer}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="244" y="0" width="234" height="14" uuid="053f4abd-5cae-48fc-981a-45d83be3d037">
											<property name="com.jaspersoft.studio.unit.width" value="px"/>
										</reportElement>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{eer.descrizione}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-" isBlankWhenNull="true">
										<reportElement key="" x="478" y="0" width="46" height="14" uuid="da566d3b-7a56-44ae-a6ad-a20afcc9bcca"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{quantita}]]></textFieldExpression>
									</textField>
								</jr:listContents>
							</jr:list>
						</componentElement>
						<componentElement>
							<reportElement positionType="Float" x="6" y="238" width="524" height="14" uuid="2158c78b-38ce-4548-b212-0e9b39c9288d">
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
								<property name="com.jaspersoft.studio.unit.width" value="px"/>
							</reportElement>
							<jr:list printOrder="Vertical">
								<datasetRun subDataset="MatDataset" uuid="77aef976-3d5c-481d-bd9d-c1958782bbff">
									<dataSourceExpression><![CDATA[$F{sezioneMat}]]></dataSourceExpression>
								</datasetRun>
								<jr:listContents height="14" width="524">
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="0" y="0" width="60" height="14" uuid="1d0a885d-485e-440e-9b2c-571e337024a7">
											<property name="com.jaspersoft.studio.unit.width" value="px"/>
										</reportElement>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{idPrevConsDett}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="60" y="0" width="418" height="14" uuid="91541e59-1c85-40fd-ba44-0f4b5fd2a2c0"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{descMatUscita}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-" isBlankWhenNull="true">
										<reportElement key="" x="478" y="0" width="46" height="14" uuid="54561ebb-94b6-4c99-87b6-0220f58135bd"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{quantita}]]></textFieldExpression>
									</textField>
								</jr:listContents>
							</jr:list>
						</componentElement>
						<componentElement>
							<reportElement positionType="Float" x="6" y="302" width="524" height="14" uuid="9f24f04c-49e9-4df0-bcde-c9a22c68d66d">
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
							</reportElement>
							<jr:list printOrder="Vertical">
								<datasetRun subDataset="RruDataset" uuid="ddb38529-7983-4b13-b849-a0b2b925035a">
									<dataSourceExpression><![CDATA[$F{sezioneRru}]]></dataSourceExpression>
								</datasetRun>
								<jr:listContents height="14" width="524">
									<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-" isBlankWhenNull="true">
										<reportElement key="" x="477" y="0" width="47" height="14" uuid="4149a0c0-5bc4-4e42-a7e0-53826adda6b3">
											<property name="com.jaspersoft.studio.unit.width" value="px"/>
										</reportElement>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{quantita}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="327" y="0" width="150" height="14" uuid="ec611e27-d6e4-44a0-a575-8b55a3fc01ee"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{destinazione}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="60" y="0" width="94" height="14" uuid="7dd54244-b9a1-4e73-84c7-2274acc36ec9"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{eer.codiceEer}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="154" y="0" width="173" height="14" uuid="f9c979ad-9782-4727-98a9-7a2ee0ae54e9"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{eer.descrizione}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="0" y="0" width="60" height="14" uuid="e0d7a048-ad4d-4563-9a41-e92e28ece795">
											<property name="com.jaspersoft.studio.unit.width" value="px"/>
										</reportElement>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{idPrevConsDett}]]></textFieldExpression>
									</textField>
								</jr:listContents>
							</jr:list>
						</componentElement>
						<componentElement>
							<reportElement positionType="Float" x="6" y="368" width="524" height="14" uuid="b36580c0-b2b7-450f-8d2c-d319447f4f5e">
								<property name="com.jaspersoft.studio.unit.height" value="px"/>
							</reportElement>
							<jr:list printOrder="Vertical">
								<datasetRun subDataset="RuDataset" uuid="6ce3932f-b9e8-4422-aebf-ae6380baf916">
									<dataSourceExpression><![CDATA[$F{sezioneRu}]]></dataSourceExpression>
								</datasetRun>
								<jr:listContents height="14" width="524">
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="154" y="0" width="173" height="14" uuid="0afc9187-e7bd-4d2d-af34-55173a4b5b6a"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{eer.descrizione}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" pattern="#,##0.###;#,##0.###-" isBlankWhenNull="true">
										<reportElement key="" x="477" y="0" width="47" height="14" uuid="d8b611da-b453-42a4-b9c0-a729dbbf0bc3"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{quantita}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="0" y="0" width="60" height="14" uuid="1f6b671b-de6c-41ef-857d-5da6411df5ca"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{idPrevConsDett}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="327" y="0" width="150" height="14" uuid="0d136e10-2a9d-4aa1-83f9-9f7b4733cd85"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{destinazione}]]></textFieldExpression>
									</textField>
									<textField textAdjust="ScaleFont" isBlankWhenNull="true">
										<reportElement key="" x="60" y="0" width="94" height="14" uuid="eaa19d81-d10a-4857-ab2f-7dde96c0193e"/>
										<box>
											<pen lineWidth="0.25"/>
										</box>
										<textElement textAlignment="Left" verticalAlignment="Bottom">
											<font isBold="true"/>
										</textElement>
										<textFieldExpression><![CDATA[$F{eer.codiceEer}]]></textFieldExpression>
									</textField>
								</jr:listContents>
							</jr:list>
						</componentElement>
					</jr:listContents>
				</jr:list>
			</componentElement>
		</band>
	</title>
</jasperReport>'
WHERE id_report = 3;
