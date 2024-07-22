// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "MeetAgainShared",
    platforms: [
        .iOS(.v10)
    ],
    products: [
        .library(
            name: "MeetAgainShared",
            targets: ["MeetAgainShared"]),
    ],
    targets: [
        .binaryTarget(
            name: "MeetAgainShared",
            url: "https://github.com/kiiskristo/KMPShared/actions/runs/10049224654/artifacts/1728013259",
            checksum: "467e25f71de3dc48dedf7105e14925ab79e4ed1fd8cba19b41150a0c4169cbd0"
        )
    ]
)